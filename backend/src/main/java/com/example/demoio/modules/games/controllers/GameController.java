package com.example.demoio.modules.games.controllers;

import com.example.demoio.core.auth.services.UserProvider;
import com.example.demoio.models.Game;
import com.example.demoio.models.UserDailyTask;
import com.example.demoio.modules.app.controllers.BaseController;
import com.example.demoio.modules.dailytasks.services.DailyTaskService;
import com.example.demoio.modules.games.repositories.GameRepository;
import com.example.demoio.modules.games.services.GameUnlockService;
import com.example.demoio.modules.ranking.services.RankingProvider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;


@Controller
@RequestMapping("/game")
public class GameController extends BaseController {
    private final RankingProvider rankingProvider;
    private final GameRepository gameRepository;
    private final UserProvider userProvider;
    private final DailyTaskService dailyTaskService;
    private final GameUnlockService gameUnlockService;

    private final String baseIframeURL = "http://localhost/levels/";

    public GameController(UserProvider userProvider, RankingProvider rankingProvider, GameRepository gameRepository, DailyTaskService dailyTaskService, GameUnlockService gameUnlockService) {
        super(userProvider);
        this.userProvider = userProvider;
        this.rankingProvider = rankingProvider;
        this.gameRepository = gameRepository;
        this.dailyTaskService = dailyTaskService;
        this.gameUnlockService = gameUnlockService;
    }

    @GetMapping("/{gameID}")
    public String gameDescriptionPage(@PathVariable Long gameID, Model model) {
        Game game = this.gameRepository.findByGameId(gameID);

        model.addAttribute("gameName", game.getName());
        model.addAttribute("gameDescription", game.getDescription());
        model.addAttribute("imageSlugName", game.getImageSlugName());
        model.addAttribute("rankingData", this.rankingProvider.getRankingByGameID(gameID));
        model.addAttribute("isGameUnlocked", this.gameUnlockService.isGameUnlocked(gameID));
        // Optional attributes
        model.addAttribute("points", this.rankingProvider.getUserBestScore(gameID, this.userProvider.getCurrentUserId()));

        Optional<UserDailyTask> currentDailyTask = this.dailyTaskService.getUserCurrentDailyTask();
        boolean isDailyTaskRelatedToGame = currentDailyTask.isPresent() && currentDailyTask.get().getDailyTask().getGame().getGameId().equals(gameID);
        if (currentDailyTask.isPresent() && isDailyTaskRelatedToGame) {
            model.addAttribute("coinReward", currentDailyTask.get().getDailyTask().getCoinsReward());
        }

        return "game";
    }

    @GetMapping("/{gameID}/play")
    public String handleGameLoadByID(@PathVariable Long gameID, Model model) {
        if (!this.gameUnlockService.isGameUnlocked(gameID)) {
            return "redirect:/game/" + gameID;
        }

        Game game = this.gameRepository.findByGameId(gameID);

        model.addAttribute("gameName", game.getName());
        model.addAttribute("gameId", gameID);
        model.addAttribute("points", this.rankingProvider.getUserBestScore(gameID, this.userProvider.getCurrentUserId()));

        model.addAttribute("iframeLevelUrl", this.baseIframeURL + game.getGameId() + "/index.html");
        model.addAttribute("iframeWidth", game.getFrameWidth());
        model.addAttribute("iframeHeight", game.getFrameHeight());

        return "game-play";
    }

}
