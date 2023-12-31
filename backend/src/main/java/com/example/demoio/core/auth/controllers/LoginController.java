package com.example.demoio.core.auth.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/")
    public String red() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "register", required = false) String registerParam, Model model) {
        if (registerParam != null) {
            model.addAttribute("showRegisteredAlert", registerParam.equals("success"));
        }

        return "login";
    }
}
