package com.example.demoio.modules.app.controllers;

import com.example.demoio.core.auth.services.UserProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class BaseController {
    private final UserProvider userProvider;

    public BaseController(UserProvider userProvider) {
        this.userProvider = userProvider;
    }

    @ModelAttribute("requestURI")
    public String requestURI(final HttpServletRequest request) {
        return request.getRequestURI();
    }

    @ModelAttribute("username")
    public String username() {
        return userProvider.getCurrentUser().getUsername();
    }

    @ModelAttribute("userCoins")
    public int userCoins() {
        return userProvider.getCurrentUser().getUserCoins();
    }
}
