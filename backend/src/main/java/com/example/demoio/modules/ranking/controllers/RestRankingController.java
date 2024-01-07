package com.example.demoio.modules.ranking.controllers;


import com.example.demoio.core.auth.services.UserProvider;
import com.example.demoio.models.Game;
import com.example.demoio.models.Ranking;
import com.example.demoio.models.User;
import com.example.demoio.modules.dailytasks.services.DailyTaskService;
import com.example.demoio.modules.games.repositories.GameRepository;
import com.example.demoio.modules.ranking.dto.UpdateRanking;
import com.example.demoio.modules.ranking.repositories.RankingRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Tag(name = "Ranking")
@RestController
@RequestMapping("/api/ranking")
public class RestRankingController {

    private final UserProvider userProvider;
    private final RankingRepository rankingRepository;
    private final GameRepository gameRepository;
    private final DailyTaskService dailyTaskService;

    @Autowired
    public RestRankingController(UserProvider userProvider, RankingRepository rankingRepository, GameRepository gameRepository, DailyTaskService dailyTaskService) {
        this.userProvider = userProvider;
        this.rankingRepository = rankingRepository;
        this.gameRepository = gameRepository;
        this.dailyTaskService = dailyTaskService;
    }

    @Operation(summary = "Zmienia ilość punktów zdobytych przez gracza w danej grze.")
    @PostMapping
    public void saveUserScore(@RequestBody UpdateRanking updateData) {
        User user = this.userProvider.getCurrentUser();
        Game game = this.gameRepository.findByGameId(updateData.gameID());

        Optional<Ranking> userRanking = this.rankingRepository.findByGameAndUser(game, user);
        if (userRanking.isEmpty()) {
            Ranking newUserRanking = new Ranking(user, game, updateData.score());
            newUserRanking.setUser(user);
            this.rankingRepository.save(newUserRanking);
        }

        if (userRanking.isPresent()) {
            boolean hasBetterScore = updateData.score() > userRanking.get().getBestScore();

            if (hasBetterScore) {
                userRanking.get().setBestScore(updateData.score());
                this.rankingRepository.save(userRanking.get());
            }
        }

        boolean isCurrentDailyTaskRelatedToGame = this.dailyTaskService.isCurrentDailyTaskRelatedToGame(updateData.gameID());
        if (isCurrentDailyTaskRelatedToGame && updateData.score() >= dailyTaskService.getMinimumScoreToCompleteDailyTask()) {
            this.dailyTaskService.markCurrentDailyTaskAsCompleted();
        }
    }
}
