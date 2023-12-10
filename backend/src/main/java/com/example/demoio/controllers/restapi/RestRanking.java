package com.example.demoio.controllers.restapi;


import com.example.demoio.models.orm.UserGames;
import com.example.demoio.repositories.UserRepository;
import com.example.demoio.models.dto.UpdateRanking;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Ranking")
@RestController
@RequestMapping("/api/ranking")
public class RestRanking extends BaseRestApiController {
    @Operation(summary = "Zmienia ilość punktów zdobytych przez gracza w danej grze.")
    @PostMapping
    public void saveUserScore(UpdateRanking updateData) {
        String username = getCurrentUserName();
        UserRepository userRepository = ctx.getBean(UserRepository.class);

        userRepository.save(new UserGames(updateData.gameID(), username, updateData.score(), updateData.coins()));
    }
}
