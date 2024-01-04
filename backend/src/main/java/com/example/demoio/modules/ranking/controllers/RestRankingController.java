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
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Ranking")
@RestController
@RequestMapping("/api/ranking")
public class RestRankingController {

    private final UserProvider userProvider;
    private final ApplicationContext ctx;


    @Autowired
    public RestRankingController(ApplicationContext ctx, UserProvider userProvider) {
        this.ctx = ctx;
        this.userProvider = userProvider;
    }

    @Operation(summary = "Zmienia ilość punktów zdobytych przez gracza w danej grze.")
    @PostMapping
    public void saveUserScore(@RequestBody UpdateRanking updateData) {
        User user = this.userProvider.getCurrentUser();
        UserRepository userRepository = ctx.getBean(UserRepository.class);
        GameProgressRepository gameProgressRepository = ctx.getBean(GameProgressRepository.class);

        GameProgress userProgress = gameProgressRepository.getUserProgressByGameId(updateData.gameID(), user.getId());
        if (userProgress != null && updateData.score() < userProgress.getBestScore()) {
            return;
        } else if (userProgress != null && updateData.score() > userProgress.getBestScore()) {
            user.setTotalUserScore(user.getTotalUserScore() - userProgress.getBestScore() + updateData.score());
            userProgress.setBestScore(updateData.score());

            userRepository.save(user);
            gameProgressRepository.save(userProgress);
            return;
        }
        if (userProgress == null) {
            GameProgress newGameProgress = new GameProgress(updateData.gameID(), updateData.score());
            newGameProgress.setUser(user);
            gameProgressRepository.save(newGameProgress);

            user.setTotalUserScore(user.getTotalUserScore() + updateData.score());
            userRepository.save(user);
        }
    }
}
