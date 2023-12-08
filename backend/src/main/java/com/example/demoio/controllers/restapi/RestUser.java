package com.example.demoio.controllers.restapi;

import com.example.demoio.User;
import com.example.demoio.UserRepository;
import com.example.demoio.models.dto.UpdateUserCoinsRequest;
import com.example.demoio.models.dto.UserDataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User")
@RestController
@RequestMapping("/api/user")
public class RestUser extends BaseRestApiController {
    @Operation(summary = "Zwraca ilość monet użytkownika.")
    @GetMapping
    public UserDataResponse returnUserInfo() {
        com.example.demoio.User user = getCurrentUser();

        return new UserDataResponse(user.getUsername(), user.getUserCoins());
    }

    @Operation(summary = "Zmienia ilość monet użytkownika.", description = "Przykłady:\n" +
            "- user miał 500 monet, w grze zużył 20, więc trzeba tu odesłać 480\n" +
            "- user miał 500 monet, w grze zdobył 50, więc trzeba tu odesłać 550")
    @Transactional
    @PostMapping("/coins")
    public void updateUserCoins(@RequestBody UpdateUserCoinsRequest userData) {
        User user = getCurrentUser();
        UserRepository userRepository = ctx.getBean(UserRepository.class);

        user.setUserCoins(userData.coins());
        userRepository.save(user);
    }
}
