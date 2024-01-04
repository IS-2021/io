package com.example.demoio.core.auth.controllers;

import com.example.demoio.models.User;
import com.example.demoio.modules.datastorage.repositories.UserRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final ApplicationContext ctx;

    public RegisterController(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @GetMapping()
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping()
    public String handleRegister(@RequestParam("username") String username,
                                 @RequestParam("password") String password) {
        User newUser = new User(username, password);
        UserRepository userRepository = ctx.getBean(UserRepository.class);
        if (userRepository.findByUsername(username) != null) {
            return "redirect:/register?error";
        }

        if (!isValidPassword(password)) {
            return "redirect:/register?bpass";
        }

        if (username.length() < 5) {
            return "redirect:/register?blogin";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(hashedPassword);

        userRepository.save(newUser);

        return "redirect:/login?register=success";
    }

    public static boolean isValidPassword(String password) {
        String regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }
}
