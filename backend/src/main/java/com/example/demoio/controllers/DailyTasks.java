package com.example.demoio.controllers;

import com.example.demoio.models.DailyTask;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/daily-tasks")
public class DailyTasks extends BaseController {

    private List<DailyTask> getDailyTasks() {

        return Arrays.asList(
                new DailyTask("Recykling", "recykling", "Coś do zrobienia z recyklingiem", "/daily-tasks/select/1"),
                new DailyTask("Oszczędzanie wody", "oszczedzanie-wody", "Coś do zrobienia z oszczędzaniem wody", "/daily-tasks/select/2")
        );
    }

    @GetMapping()
    public String showDailyTasksPage(Model model) {

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
