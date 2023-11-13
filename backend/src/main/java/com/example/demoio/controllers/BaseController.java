package com.example.demoio.controllers;

import com.example.demoio.User;
import com.example.demoio.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class BaseController {

    @Autowired
    private ApplicationContext ctx;

    private int userCoins;

    private String username;

    @ModelAttribute("requestURI")
    public String requestURI(final HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserRepository userRepository = ctx.getBean(UserRepository.class);
        User user =  userRepository.findByUsername(auth.getName());
        userCoins = (int) user.getUserCoins();
        username = user.getUsername();

        return request.getRequestURI();
    }

    @ModelAttribute("username")
    public String username() {


        return username;
    }

    @ModelAttribute("userCoins")
    public int userCoins() {

        return userCoins;
    }
}
