package com.example.demoio.modules.games.controllers;

import com.example.demoio.modules.app.controllers.BaseController;
import com.example.demoio.modules.datastorage.repositories.UserRepository;
import com.example.demoio.modules.ranking.services.RankingProvider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/game")
public class GameController extends BaseController {
    private final RankingProvider rankingProvider;

    public GameController(UserRepository userRepository, RankingProvider rankingProvider) {
        super(userRepository);
        this.rankingProvider = rankingProvider;
    }

    @GetMapping("/{gameID}")
    public String gameDescriptionPage(@PathVariable int gameID, Model model) {

        model.addAttribute("gameName", "Oszczędzanie wody");
        model.addAttribute("gameDescription", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        model.addAttribute("imageSlugName", "oszczedzanie-wody");
        model.addAttribute("rankingData", this.rankingProvider.getRankingByGameID(gameID));
        // Optional attributes
        model.addAttribute("points", 420);
        model.addAttribute("coinReward", 3);

        return "game";
    }

    @GetMapping("/{gameID}/play")
    public String handleGameLoadByID(@PathVariable int gameID) {
        return switch (gameID) {
            case 1 -> "firstGame";
            case 2 -> "secondGame";
            case 3 -> "thirdGame";
            case 4 -> "fourthGame";
            case 5 -> "fifthGame";
            case 6 -> "sixthGame";
            case 7 -> "seventhGame";
            default -> "notFound";
        };
    }

}
