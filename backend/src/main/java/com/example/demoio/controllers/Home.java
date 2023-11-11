package com.example.demoio.controllers;

import com.example.demoio.User;
import com.example.demoio.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class Home extends BaseController {

    @Autowired
    private ApplicationContext ctx;
    @GetMapping()
    public String showHomepage(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserRepository userRepository = ctx.getBean(UserRepository.class);
        User user =  userRepository.findByUsername(auth.getName());

        model.addAttribute("username",user.getUsername());
        model.addAttribute("userCoins",user.getUserCoins());


        return "home";
    }
}
