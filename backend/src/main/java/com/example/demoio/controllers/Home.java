package com.example.demoio.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Home extends BaseController {
    @GetMapping("/home")
    public String checkAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return "home";
        }
        return "login";
    }

    @PostMapping("/home")
    public String mainPagePost() {
        System.out.println("notAuthorized post req");

        return "poziom1";
    }
}
