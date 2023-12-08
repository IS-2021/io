package com.example.demoio.controllers.restapi;

import com.example.demoio.UserRepository;
import com.example.demoio.models.restapi.UserData;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class User extends BaseRestApiController {
    /**
     * Zwraca ilość monet użytkownika.
     */
    @GetMapping("")
    public UserData returnUserInfo() {
        com.example.demoio.User user = getCurrentUser();

        return new UserData(user.getUsername(), user.getUserCoins());
    }

    /**
     * Zmienia ilość monet użytkownika.
     * Przykłady:
     * - user miał 500 monet, w grze zużył 20, więc trzeba tu odesłać 480
     * - user miał 500 monet, w grze zdobył 50, więc trzeba tu odesłać 550
     *
     * @param userCoins Ilość monet do zapisania
     */
    @PostMapping("/coins")
    public void updateUserCoins(@RequestBody int userCoins) {
        String username = getCurrentUserName();
        UserRepository userRepository = ctx.getBean(UserRepository.class);

        userRepository.updateUserCoins(userCoins, username);
    }
}
