package com.example.demoio.modules.app.controllers;

import com.example.demoio.core.auth.services.UserProvider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {
    public HomeController(UserProvider userProvider) {
        super(userProvider);
    }

    @GetMapping()
    public String showHomepage(Model model) {
        return "home";
    }
}
