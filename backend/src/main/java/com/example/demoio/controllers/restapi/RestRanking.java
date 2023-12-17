package com.example.demoio.controllers.restapi;


import com.example.demoio.models.orm.GameProgress;
import com.example.demoio.repositories.UserRepository;
import com.example.demoio.models.dto.UpdateRanking;
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
        String username = getCurrentUserName();
        UserRepository userRepository = ctx.getBean(UserRepository.class);

        // TODO: Remove coins from this object
//        userRepository.save(new GameProgress(updateData.gameID(), username, updateData.score()));
    }
}
