package com.example.demoio.controllers.restapi;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/ranking")
public class Ranking extends BaseRestApiController {
    @PostMapping("/:id")
    public String handleSave() {

        return "tt";
    }
}
