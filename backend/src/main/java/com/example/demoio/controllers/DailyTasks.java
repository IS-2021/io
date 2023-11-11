package com.example.demoio.controllers;

import com.example.demoio.User;
import com.example.demoio.UserRepository;
import com.example.demoio.models.DailyTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private ApplicationContext ctx;
    private List<DailyTask> getDailyTasks() {

        return Arrays.asList(
                new DailyTask("Recykling", "recykling", "Coś do zrobienia z recyklingiem", "/daily-tasks/select/1"),
                new DailyTask("Oszczędzanie wody", "oszczedzanie-wody", "Coś do zrobienia z oszczędzaniem wody", "/daily-tasks/select/2")
        );
    }

    @GetMapping()
    public String showDailyTasksPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserRepository userRepository = ctx.getBean(UserRepository.class);
        User user =  userRepository.findByUsername(auth.getName());

        model.addAttribute("username",user.getUsername());
        model.addAttribute("userCoins",user.getUserCoins());
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
