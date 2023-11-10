package com.example.demoio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/daily-tasks")
public class DailyTasks extends BaseController {
    @GetMapping()
    public String showDailyTasksPage(Model model) {
        model.addAttribute("coinReward", 2);

        return "daily-tasks";
    }
}
