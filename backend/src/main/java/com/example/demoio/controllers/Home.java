package com.example.demoio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class Home extends BaseController {
    @GetMapping()
    public String showHomepage() {
        return "home";
    }
}
