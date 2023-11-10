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
@RequestMapping("/ranking")
public class Ranking extends BaseController {
    private List<DisplayRankingData> getOverallRankingData() {
        // Ideally this list should be sorted by score
        return Arrays.asList(
                new DisplayRankingData("testUser", 340, 1),
                new DisplayRankingData("foo", 33, 0),
                new DisplayRankingData("bar", 66, 1)
        );
    }

    public static List<DisplayRankingData> getRankingByGameID(int gameID) {
        // Ideally this list should be sorted by score
        return Arrays.asList(
                new DisplayRankingData("game", 340, 1),
                new DisplayRankingData("ranking", 33, 0),
                new DisplayRankingData("data", 66, 1)
        );
    }

    @GetMapping()
    public String showTotalRankingPage(Model model) {
        model.addAttribute("rankingData", getOverallRankingData());

        return "ranking";
    }

    @GetMapping("/{gameID}")
    public String showTotalRankingPage(@PathVariable int gameID, Model model) {
        model.addAttribute("rankingData", getRankingByGameID(gameID));

        return "ranking";
    }
}
