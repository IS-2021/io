package com.example.demoio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class Login {
    @GetMapping()
    public String loginPage(@RequestParam(value = "register", required = false) String registerParam, Model model) {
        if (registerParam != null) {
            model.addAttribute("showRegisteredAlert", registerParam.equals("success"));
        }

        return "login";
    }
}
