package com.example.demoio.controllers;

import com.example.demoio.models.orm.User;
import com.example.demoio.repositories.UserRepository;
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

    private String username;

    private int userCoins;

    public void setValues() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserRepository userRepository = ctx.getBean(UserRepository.class);
        User user =  userRepository.findByUsername(auth.getName());

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
