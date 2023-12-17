package com.example.demoio.controllers;

import com.example.demoio.models.orm.GameProgress;
import com.example.demoio.models.orm.User;
import com.example.demoio.models.thymeleaf.DisplayRankingData;
import com.example.demoio.repositories.GameProgressRepository;
import com.example.demoio.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ranking")
public class Ranking extends BaseController {

    @Autowired
    private ApplicationContext ctx;


    public List<DisplayRankingData> getRankingByGameID(int gameID, ApplicationContext ctx) {
        GameProgressRepository gameProgressRepository = ctx.getBean(GameProgressRepository.class);

        Pageable pageable = PageRequest.of(0,1);
        List<GameProgress> gameProgressesList = gameProgressRepository.getProgressByGameId(gameID, pageable);
        List<DisplayRankingData> recordList = new ArrayList<>();

        for (GameProgress gameProgress : gameProgressesList) {
            DisplayRankingData drd = new DisplayRankingData(
                    gameProgress.getUsername(),
                    gameProgress.getBestScore());
            recordList.add(drd);
        }

        return recordList;
    }

    private List<DisplayRankingData> getOverallRankingData() {
        UserRepository userRepository = ctx.getBean(UserRepository.class);

        List<User> userRank = userRepository.findTop10ByScore();
        List<DisplayRankingData> recordList = new ArrayList<>();

        for (User user : userRank) {
            DisplayRankingData drd = new DisplayRankingData(
                    user.getUsername(),
                    user.getTotalUserScore());
            recordList.add(drd);
        }

        return recordList;
    }


    @GetMapping()
    public String showTotalRankingPage(Model model) {
        model.addAttribute("rankingData", getOverallRankingData());

        return "ranking";
    }

    @GetMapping("/{gameID}")
    public String showTotalRankingPage(@PathVariable int gameID, Model model) {

        model.addAttribute("rankingData", getRankingByGameID(gameID, this.ctx));

        return "ranking";
    }
}
