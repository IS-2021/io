package com.example.demoio.modules.app.controllers;

import com.example.demoio.core.auth.services.UserProvider;
import com.example.demoio.models.User;
import com.example.demoio.modules.app.dto.UpdateUserCoinsRequest;
import com.example.demoio.modules.app.dto.UserDataResponse;
import com.example.demoio.modules.datastorage.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User")
@RestController
@RequestMapping("/api/user")
public class RestUserController {
    private final UserProvider userProvider;
    private final UserRepository userRepository;


    @Autowired
    public RestUserController(UserProvider userProvider, UserRepository userRepository) {
        this.userProvider = userProvider;
        this.userRepository = userRepository;
    }

    @Operation(summary = "Zwraca ilość monet użytkownika.")
    @GetMapping
    public UserDataResponse returnUserInfo() {
        User user = this.userProvider.getCurrentUser();

        return new UserDataResponse(user.getUsername(), user.getUserCoins());
    }

    @Operation(summary = "Zmienia ilość monet użytkownika.", description = """
            Przykłady:
            - user miał 500 monet, w grze zużył 20, więc trzeba tu odesłać 480
            - user miał 500 monet, w grze zdobył 50, więc trzeba tu odesłać 550""")
    @Transactional
    @PostMapping("/coins")
    public void updateUserCoins(@RequestBody UpdateUserCoinsRequest userData) {
        User user = this.userProvider.getCurrentUser();

        user.setUserCoins(userData.coins());
        this.userRepository.save(user);
    }
}
