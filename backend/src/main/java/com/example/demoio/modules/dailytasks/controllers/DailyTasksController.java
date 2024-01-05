package com.example.demoio.modules.dailytasks.controllers;

import com.example.demoio.core.auth.services.UserProvider;
import com.example.demoio.models.Game;
import com.example.demoio.models.GameUnlock;
import com.example.demoio.models.User;
import com.example.demoio.modules.app.controllers.BaseController;
import com.example.demoio.modules.dailytasks.dto.DailyTaskDTO;
import com.example.demoio.modules.dailytasks.repositories.DailyTaskRepository;
import com.example.demoio.modules.dailytasks.services.DailyTaskService;
import com.example.demoio.modules.games.repositories.GameRepository;
import com.example.demoio.modules.games.services.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/daily-tasks")
public class DailyTasksController extends BaseController {

    private final UserProvider userProvider;
    private final GameService gameService;
    private final GameRepository gameRepository;

    private final DailyTaskRepository dailyTaskRepository;
    private final DailyTaskService dailyTaskService;

    public DailyTasksController(UserProvider userProvider, GameService gameService, GameRepository gameRepository, DailyTaskRepository dailyTaskRepository, DailyTaskService dailyTaskService) {
        super(userProvider);
        this.userProvider = userProvider;
        this.gameService = gameService;
        this.gameRepository = gameRepository;
        this.dailyTaskRepository = dailyTaskRepository;
        this.dailyTaskService = dailyTaskService;
    }

    private List<DailyTaskDTO> getDailyTasks() {
        User user = userProvider.getCurrentUser();
        LocalDate currentDate = LocalDate.now();

        if (dailyTaskService.areDailyTasksGeneratedForUser(user, currentDate)) {
            return dailyTaskService.getDailyTasksForUser(user, currentDate);
        } else {
            List<GameUnlock> passedGames = gameService.getPassedGamesForUser(user);
            List<Game> allGames = gameRepository.findAll();

            List<Game> availableGames = allGames.stream()
                    .filter(game -> passedGames.stream().noneMatch(unlock -> unlock.getGame().equals(game)))
                    .toList();

            List<Game> limitedAvailableGames = availableGames.stream().limit(2).toList();

            List<Long> dailyTaskIds = dailyTaskRepository.findAllDailyTaskIds();

            List<DailyTaskDTO> newTasks = new ArrayList<>();

            for (int i = 0; i < limitedAvailableGames.size(); i++) {
                Long taskId = dailyTaskIds.get(i);
                Game game = limitedAvailableGames.get(i);

                String taskName = game.getName();
                String taskLink = "/daily-tasks/select/" + game.getGameId();
                newTasks.add(new DailyTaskDTO(taskId, taskName, game.getImageSlugName(), game.getDescription(), taskLink));
            }

            dailyTaskService.saveDailyTasksForUser(user, currentDate, newTasks);

            return newTasks;
        }
    }

    @GetMapping()
    public String showDailyTasksPage(Model model) {

        System.out.println(getDailyTasks().toString());
        model.addAttribute("coinReward", 2);
        model.addAttribute("availableTasks", getDailyTasks());

        return "daily-tasks";
    }

    @PostMapping()
    public String handleDailyTaskSelect(@RequestParam("callbackValue") String callbackValue) {
        System.out.println("daily-task:POST - " + callbackValue);

        return "redirect:/daily-tasks";
    }
}
