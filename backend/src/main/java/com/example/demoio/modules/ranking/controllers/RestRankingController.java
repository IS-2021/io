package com.example.demoio.modules.ranking.controllers;


import com.example.demoio.core.auth.services.UserProvider;
import com.example.demoio.models.GameProgress;
import com.example.demoio.models.User;
import com.example.demoio.modules.datastorage.repositories.UserRepository;
import com.example.demoio.modules.ranking.dto.UpdateRanking;
import com.example.demoio.modules.ranking.repositories.GameProgressRepository;
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
    private final GameProgressRepository gameProgressRepository;


    @Autowired
    public RestRankingController(UserProvider userProvider, UserRepository userRepository, GameProgressRepository gameProgressRepository) {
        this.userProvider = userProvider;
        this.userRepository = userRepository;
        this.gameProgressRepository = gameProgressRepository;
    }

    @Operation(summary = "Zmienia ilość punktów zdobytych przez gracza w danej grze.")
    @PostMapping
    public void saveUserScore(@RequestBody UpdateRanking updateData) {
        User user = this.userProvider.getCurrentUser();

        GameProgress userProgress = this.gameProgressRepository.getUserProgressByGameId(updateData.gameID(), user.getId());
        if (userProgress != null && updateData.score() < userProgress.getBestScore()) {
            return;
        } else if (userProgress != null && updateData.score() > userProgress.getBestScore()) {
            user.setTotalUserScore(user.getTotalUserScore() - userProgress.getBestScore() + updateData.score());
            userProgress.setBestScore(updateData.score());

            this.userRepository.save(user);
            this.gameProgressRepository.save(userProgress);
            return;
        }
        if (userProgress == null) {
            GameProgress newGameProgress = new GameProgress(updateData.gameID(), updateData.score());
            newGameProgress.setUser(user);
            this.gameProgressRepository.save(newGameProgress);

            user.setTotalUserScore(user.getTotalUserScore() + updateData.score());
            this.userRepository.save(user);
        }
    }
}
