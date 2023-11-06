package com.example.demoio;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@org.springframework.stereotype.Controller
public class Controller {


    @GetMapping("/mainPage")
    public String checkAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!(auth instanceof AnonymousAuthenticationToken)) {
            return "mainPage";
        }
        return "login";

    }

    @PostMapping("/mainPage")
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
        if(!(auth instanceof AnonymousAuthenticationToken)) {
            return "mainPage";
        }
        return "redirect:login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


}
