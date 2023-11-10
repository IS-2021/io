package com.example.demoio.controllers;

import com.example.demoio.models.DisplayRankingData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/game")
public class Game extends BaseController {
    private List<DisplayRankingData> getRankingData() {
        // Ideally this list should be sorted by score
        return Arrays.asList(
                new DisplayRankingData("testUser", 340, 1),
                new DisplayRankingData("foo", 33, 0),
                new DisplayRankingData("bar", 66, 1)
        );
    }

    @GetMapping("/{id}")
    public String gameDescriptionPage(@PathVariable int id, Model model) {
        model.addAttribute("gameName", "Oszczędzanie wody");
        model.addAttribute("gameDescription", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        model.addAttribute("imageSlugName", "oszczedzanie-wody");
        model.addAttribute("rankingData", getRankingData());

        // Optional attributes
        model.addAttribute("points", 420);
        model.addAttribute("coinReward", 3);

        return "game";
    }
}
