package com.example.demoio.modules.games.controllers;

import com.example.demoio.modules.app.controllers.BaseController;
import com.example.demoio.modules.ranking.controllers.RankingController;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/game")
public class GameController extends BaseController {

    private final ApplicationContext ctx;

    public GameController(ApplicationContext ctx) {
        super(ctx);
        this.ctx = ctx;
    }

    @GetMapping("/{gameID}")
    public String gameDescriptionPage(@PathVariable int gameID, Model model) {

        RankingController r = new RankingController(this.ctx);
        model.addAttribute("gameName", "Oszczędzanie wody");
        model.addAttribute("gameDescription", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        model.addAttribute("imageSlugName", "oszczedzanie-wody");
        model.addAttribute("rankingData", r.getRankingByGameID(gameID, ctx));
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
