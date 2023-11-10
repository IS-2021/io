package com.example.demoio.controllers;

import com.example.demoio.User;
import com.example.demoio.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class Register {
    @Autowired
    private ApplicationContext ctx;

    @GetMapping()
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping()
    public String handleRegister(@RequestParam("username") String username, @RequestParam("password") String password) {
        User newUser = new User(username, password);
        UserRepository userRepository = ctx.getBean(UserRepository.class);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(hashedPassword);

        userRepository.save(newUser);

        return "redirect:/login?register=success";
    }
}
