package com.example.demoio.modules.ranking.controllers;


import com.example.demoio.core.auth.services.UserProvider;
import com.example.demoio.models.Game;
import com.example.demoio.models.Ranking;
import com.example.demoio.models.User;
import com.example.demoio.core.auth.repositories.UserRepository;
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

@Tag(name = "Ranking")
@RestController
@RequestMapping("/api/ranking")
public class RestRankingController {

    private final UserProvider userProvider;
    private final UserRepository userRepository;
    private final RankingRepository rankingRepository;
    private final GameRepository gameRepository;


    @Autowired
    public RestRankingController(UserProvider userProvider, UserRepository userRepository, RankingRepository rankingRepository, GameRepository gameRepository) {
        this.userProvider = userProvider;
        this.userRepository = userRepository;
        this.rankingRepository = rankingRepository;
        this.gameRepository = gameRepository;
    }

    @Operation(summary = "Zmienia ilość punktów zdobytych przez gracza w danej grze.")
    @PostMapping
    public void saveUserScore(@RequestBody UpdateRanking updateData) {
        User user = this.userProvider.getCurrentUser();
        Game game = this.gameRepository.findByGameId(updateData.gameID());

//        Ranking userProgress = this.rankingRepository.findByGameAndUser(updateData.gameID(), user.getId());
        Ranking userRanking = this.rankingRepository.findByGameAndUser(game, user);
        if (userRanking != null && updateData.score() < userRanking.getBestScore()) {
            return;
        } else if (userRanking != null && updateData.score() > userRanking.getBestScore()) {
//            user.setTotalUserScore(user.getTotalUserScore() - userProgress.getBestScore() + updateData.score());
            userRanking.setBestScore(updateData.score());

//            this.userRepository.save(user);
            this.rankingRepository.save(userRanking);
            return;
        }
        if (userRanking == null) {
            Ranking newGameProgress = new Ranking(user, game, updateData.score());
            newGameProgress.setUser(user);
            this.rankingRepository.save(newGameProgress);

//            user.setTotalUserScore(user.getTotalUserScore() + updateData.score());
//            this.userRepository.save(user);
        }
    }
}
