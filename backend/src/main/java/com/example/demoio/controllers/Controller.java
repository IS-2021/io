package com.example.demoio.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.stereotype.Controller
public class Controller {
    @ModelAttribute("requestURI")
    public String requestURI(final HttpServletRequest request) {
        return request.getRequestURI();
    }

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

    @PostMapping("/register")
    public String registerNewUser(
            @RequestParam String username,
            @RequestParam String password
    ) {
        System.out.println("notAuthorized post req");

        return "poziom1";
    }

    @GetMapping()
    public String defaultPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return "home";
        }
        return "redirect:login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
