package com.example.demoio.controllers;

import com.example.demoio.models.User_Games;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/game")
public class Game extends BaseController {


    @GetMapping("/{gameID}")
    public String gameDescriptionPage(@PathVariable int gameID, Model model) {

        Ranking r = new Ranking();
        model.addAttribute("gameName", "Oszczędzanie wody");
        model.addAttribute("gameDescription", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        model.addAttribute("imageSlugName", "oszczedzanie-wody");
        //model.addAttribute("rankingData", r.getRankingByGameID(gameID));

        // Optional attributes
        model.addAttribute("points", 420);
        model.addAttribute("coinReward", 3);

        return "game";
    }
}
