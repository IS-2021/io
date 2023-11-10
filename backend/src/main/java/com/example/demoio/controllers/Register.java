package com.example.demoio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class Register {
    @GetMapping()
    public String handleRegister() {
        return "register";
    }
}
