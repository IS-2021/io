package com.example.demoio.modules.dailytasks.controllers;

import com.example.demoio.core.auth.services.UserProvider;
import com.example.demoio.modules.app.controllers.BaseController;
import com.example.demoio.modules.dailytasks.services.DailyTaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/daily-tasks")
public class DailyTasksController extends BaseController {
    private final DailyTaskService dailyTaskService;

    public DailyTasksController(DailyTaskService dailyTaskService, UserProvider userProvider) {
        super(userProvider);

        this.dailyTaskService = dailyTaskService;
    }

    @GetMapping()
    public String showDailyTasksPage(Model model) {
        model.addAttribute("availableTasks", this.dailyTaskService.getUserDailyTasksDTO());

        return "daily-tasks";
    }

    @PostMapping()
    public String handleDailyTaskSelect(@RequestParam("callbackValue") String callbackValue) {
        this.dailyTaskService.saveDailyTasksForUser(Long.parseLong(callbackValue));

        return "redirect:/daily-tasks";
    }
}
