package com.example.demoio.controllers;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class RegisterForm {

    @GetMapping("/register")
    public String handleRegister() {

        return "register";
    }
}
