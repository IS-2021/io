package com.example.demoio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/game")
public class Game extends BaseController {
    @GetMapping("/{id}")
    public String gameDescriptionPage(@PathVariable int id, Model model) {
        model.addAttribute("points", 420);
        model.addAttribute("gameName", "Oszczędzanie wody");
        // TODO: Integrate into template
//        model.addAttribute("gameDescription", "Description");
        model.addAttribute("imageSlugName", "oszczedzanie-wody");

        return "game";
    }
}
