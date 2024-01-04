package com.example.demoio.modules.app.controllers;

import com.example.demoio.modules.datastorage.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {
    public HomeController(UserRepository userRepository) {
        super(userRepository);
    }

    @GetMapping()
    public String showHomepage(Model model) {
        return "home";
    }
}
