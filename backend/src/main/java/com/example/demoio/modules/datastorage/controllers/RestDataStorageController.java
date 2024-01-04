package com.example.demoio.modules.datastorage.controllers;

import com.example.demoio.modules.datastorage.dto.GameData;
import com.example.demoio.modules.datastorage.dto.SaveGameData;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Data Storage")
@RestController
@RequestMapping("/api/datastorage")
public class RestDataStorageController {
    public RestDataStorageController() {
    }

    @GetMapping
    public GameData returnGameData() {
        String placeholderJSON = "{\"hello\": \"world\"}";
        return new GameData(1, placeholderJSON);
    }

    @PostMapping
    public void saveGameData(@RequestBody SaveGameData saveGameData) {

    }
}
