package com.example.demoio.controllers.restapi;

import com.example.demoio.User;
import com.example.demoio.UserRepository;
import com.example.demoio.models.User_Games;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BaseRestApiController {

    @Autowired
    private ApplicationContext ctx;

    private User getCurrentUser() {
        String username = getCurrentUserName();

        UserRepository userRepository = ctx.getBean(UserRepository.class);
        return userRepository.findByUsername(username);
    }

    private String getCurrentUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    /**
     * Zwraca ilość monet użytkownika.
     */
    @GetMapping("/getUserCoins")
    public String returnUserCoins() {
        User user = getCurrentUser();

        return String.valueOf(user.getUserCoins());
    }

    /**
     * Zmienia ilość monet użytkownika.
     * Przykłady:
     * - user miał 500 monet, w grze zużył 20, więc trzeba tu odesłać 480
     * - user miał 500 monet, w grze zdobył 50, więc trzeba tu odesłać 550
     *
     * @param userCoins Ilość monet do zapisania
     */
    @PostMapping("/saveUserCoins")
    public void editUserCoins(@RequestBody int userCoins) {
        String username = getCurrentUserName();
        UserRepository userRepository = ctx.getBean(UserRepository.class);

        userRepository.updateUserCoins(userCoins, username);
    }

    /**
     * Zmienia ilość punktów zdobytych przez gracza w danej grze.
     *
     * @param gameID ID gry
     * @param coins Ilość monet zdobytych przez gracza
     * @param score Wynik gracza w danej grze
     */
    @PostMapping("/saveUserScore")
    public void saveUserScore(@RequestBody int gameID, int coins, double score) {
        String username = getCurrentUserName();
        UserRepository userRepository = ctx.getBean(UserRepository.class);

        userRepository.save(new User_Games(gameID, username, score, coins));
    }
}
