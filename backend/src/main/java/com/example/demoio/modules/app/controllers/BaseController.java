package com.example.demoio.modules.app.controllers;

import com.example.demoio.models.User;
import com.example.demoio.modules.datastorage.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class BaseController {
    private final UserRepository userRepository;

    public BaseController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private String username;

    private int userCoins;

    private void setValues() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userRepository.findByUsername(auth.getName());

        username = user.getUsername();
        userCoins = user.getUserCoins();

    }

    @ModelAttribute("requestURI")
    public String requestURI(final HttpServletRequest request) {
        return request.getRequestURI();
    }

    @ModelAttribute("username")
    public String username() {
        setValues();

        return username;
    }

    @ModelAttribute("userCoins")
    public int userCoins() {
        setValues();

        return userCoins;
    }
}
