package com.example.demoio.modules.app.controllers;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {

    final ApplicationContext ctx;

    public HomeController(ApplicationContext ctx, ApplicationContext ctx1) {
        super(ctx);
        this.ctx = ctx1;
    }

    @GetMapping()
    public String showHomepage(Model model) {
        return "home";
    }
}
