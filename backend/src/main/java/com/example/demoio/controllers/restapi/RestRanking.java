package com.example.demoio.controllers.restapi;


import com.example.demoio.models.dto.UpdateRanking;
import com.example.demoio.models.orm.GameProgress;
import com.example.demoio.models.orm.User;
import com.example.demoio.repositories.GameProgressRepository;
import com.example.demoio.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Ranking")
@RestController
@RequestMapping("/api/ranking")
public class RestRanking extends BaseRestApiController {
    @Operation(summary = "Zmienia ilość punktów zdobytych przez gracza w danej grze.")
    @PostMapping
    public void saveUserScore(@RequestBody UpdateRanking updateData) {
        User user = getCurrentUser();
        UserRepository userRepository = ctx.getBean(UserRepository.class);
        GameProgressRepository gameProgressRepository = ctx.getBean(GameProgressRepository.class);
        
        GameProgress newGameProgress = new GameProgress(updateData.gameID(), updateData.score());
        newGameProgress.setUser(user);
        gameProgressRepository.save(newGameProgress);

        user.setTotalUserScore(user.getTotalUserScore() + updateData.score());
        userRepository.save(user);
    }
}
