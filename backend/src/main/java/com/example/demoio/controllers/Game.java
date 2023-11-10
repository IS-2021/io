package com.example.demoio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.example.demoio.controllers.Ranking.getRankingByGameID;

@Controller
@RequestMapping("/game")
public class Game extends BaseController {
    @GetMapping("/{gameID}")
    public String gameDescriptionPage(@PathVariable int gameID, Model model) {
        model.addAttribute("gameName", "Oszczędzanie wody");
        model.addAttribute("gameDescription", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        model.addAttribute("imageSlugName", "oszczedzanie-wody");
        model.addAttribute("rankingData", getRankingByGameID(gameID));

        // Optional attributes
        model.addAttribute("points", 420);
        model.addAttribute("coinReward", 3);

        return "game";
    }
}
