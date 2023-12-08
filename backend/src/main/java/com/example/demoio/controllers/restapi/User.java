package com.example.demoio.controllers.restapi;

import com.example.demoio.UserRepository;
import com.example.demoio.models.dto.UpdateUserCoinsRequest;
import com.example.demoio.models.dto.UserDataResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class User extends BaseRestApiController {
    /**
     * Zwraca ilość monet użytkownika.
     */
    @GetMapping("")
    public UserDataResponse returnUserInfo() {
        com.example.demoio.User user = getCurrentUser();

        return new UserDataResponse(user.getUsername(), user.getUserCoins());
    }

    /**
     * Zmienia ilość monet użytkownika.
     * Przykłady:
     * - user miał 500 monet, w grze zużył 20, więc trzeba tu odesłać 480
     * - user miał 500 monet, w grze zdobył 50, więc trzeba tu odesłać 550
     *
     * @param userData Obiekt zawierający ilość monet do dodania/odjęcia.
     */
    @PostMapping("/coins")
    public void updateUserCoins(@RequestBody UpdateUserCoinsRequest userData) {
        String username = getCurrentUserName();
        UserRepository userRepository = ctx.getBean(UserRepository.class);

        userRepository.updateUserCoins(userData.coins(), username);
    }
}
