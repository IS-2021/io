package com.example.demoio.controllers;

import com.example.demoio.User;
import com.example.demoio.UserRepository;
import com.example.demoio.models.DisplayRankingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/ranking")
public class Ranking extends BaseController {

    @Autowired
    private ApplicationContext ctx;

    private List<DisplayRankingData> getOverallRankingData() {
        UserRepository userRepository = ctx.getBean(UserRepository.class);

        List<User> userRank = userRepository.findTop10ByScore();

        List<DisplayRankingData> recordList = new ArrayList<>();

        for (User user : userRank) {
            DisplayRankingData drd = new DisplayRankingData(user.getUsername(),
                    user.getUserScore(),
                    user.getUserCoins());
            recordList.add(drd);
        }

        return recordList;
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
