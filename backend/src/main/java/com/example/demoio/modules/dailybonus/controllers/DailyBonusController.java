package com.example.demoio.modules.dailybonus.controllers;

import com.example.demoio.core.auth.services.UserProvider;
import com.example.demoio.models.User;
import com.example.demoio.modules.app.controllers.BaseController;
import com.example.demoio.modules.dailybonus.services.DailyBonusService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/daily-bonus")
public class DailyBonusController extends BaseController {
    private final DailyBonusService dailyBonusService;
    private final UserProvider userProvider;

    public DailyBonusController(UserProvider userProvider, DailyBonusService dailyBonusService, UserProvider userProvider1) {
        super(userProvider);

        this.dailyBonusService = dailyBonusService;
        this.userProvider = userProvider1;
    }

    @GetMapping()
    public String getIndex(Model model) {
        User user = userProvider.getCurrentUser();

        model.addAttribute("dailyBonuses", dailyBonusService.getDailyBonuses());
        if (user.getLastBonusClaimedOn() != null) {
            model.addAttribute("nextBonusAvailableAt", user.getLastBonusClaimedOn().plusDays(1).truncatedTo(java.time.temporal.ChronoUnit.DAYS).toInstant(java.time.ZoneOffset.UTC).toEpochMilli());
        }

        return "daily-bonus";
    }

    @PostMapping()
    public String postClaim(@RequestParam("day") String day) {
        dailyBonusService.claimUserBonus(Integer.parseInt(day));

        return "redirect:/daily-bonus";
    }
}
