package com.example.demoio.modules.ranking.controllers;

import com.example.demoio.core.auth.services.UserProvider;
import com.example.demoio.modules.app.controllers.BaseController;
import com.example.demoio.modules.ranking.services.RankingProvider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ranking")
public class RankingController extends BaseController {
    private final RankingProvider rankingProvider;

    public RankingController(UserProvider userProvider, RankingProvider rankingProvider) {
        super(userProvider);
        this.rankingProvider = rankingProvider;
    }


    @GetMapping()
    public String showTotalRankingPage(Model model) {
        model.addAttribute("rankingData", this.rankingProvider.getOverallRankingData());

        return "ranking";
    }

    @GetMapping("/{gameID}")
    public String showTotalRankingPage(@PathVariable Long gameID, Model model) {
        model.addAttribute("rankingData", this.rankingProvider.getRankingByGameID(gameID));

        return "ranking";
    }
}
