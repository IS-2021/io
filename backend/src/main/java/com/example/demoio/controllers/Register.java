package com.example.demoio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Register {
    @GetMapping("/register")
    public String handleRegister() {

        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(
            @RequestParam String username,
            @RequestParam String password
    ) {
        System.out.println("notAuthorized post req");

        return "poziom1";
    }
}
