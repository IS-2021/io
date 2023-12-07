package com.example.demoio.controllers.restapi;

import com.example.demoio.User;
import com.example.demoio.UserRepository;
import com.example.demoio.models.User_Games;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseRestApiController {

    @Autowired
    private ApplicationContext ctx;

    @GetMapping("/api/getUserCoins")
    public String returnUserCoins() {
        // robicie tutaj get i zostanie wam zwrocona liczba coinsow aktualnie grajacego usera sparsowana do stringa
        System.out.println("próba get");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserRepository userRepository = ctx.getBean(UserRepository.class);
        User user =  userRepository.findByUsername(auth.getName());

        return String.valueOf(user.getUserCoins());
    }

    @PostMapping("/api/saveUserCoins")
    public void editUserCoins(@RequestBody int userCoins) {
        //user mial 500 coinsow, w grze zuzyl 20, wiec trzeba tu odeslac 480 i zostanie to podmienione w bazie
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserRepository userRepository = ctx.getBean(UserRepository.class);
        userRepository.updateUserCoins(userCoins, auth.getName());
    }

    @PostMapping("/api/saveUserScore")
    public void saveUserScore(@RequestBody int gameID,int coins,double score) {
        // user ukonczyl gre, wiec tutaj przesylamy id swojej gry, ile zdobyl coinsow i jaki wynik osiagnal,
        // zostanie wtedy zapisany do bazy: id gry, ile coinsow dostal, jaki wynik zdobyl, jego nick zostanie dodanie automatycznie
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserRepository userRepository = ctx.getBean(UserRepository.class);
        userRepository.save(new User_Games(gameID, auth.getName(),score,coins));
    }
}
