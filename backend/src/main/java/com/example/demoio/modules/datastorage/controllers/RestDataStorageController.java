package com.example.demoio.modules.datastorage.controllers;

import com.example.demoio.modules.datastorage.dto.GameDataRes;
import com.example.demoio.modules.datastorage.dto.SaveGameData;
import com.example.demoio.modules.datastorage.services.DataStorageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "Data Storage")
@RestController
@RequestMapping("/api/datastorage")
public class RestDataStorageController {
    private final DataStorageService dataStorageService;

    public RestDataStorageController(DataStorageService dataStorageService) {
        this.dataStorageService = dataStorageService;
    }

    @GetMapping("/{gameId}")
    public GameDataRes returnGameData(@PathVariable Long gameId) {
        Optional<String> gameDataJSON = dataStorageService.getCurrentUserGameData(gameId);

        return new GameDataRes(gameDataJSON.orElse(null));
    }

    @PostMapping
    public void saveGameData(@RequestBody SaveGameData saveGameData) {
        dataStorageService.saveGameData(saveGameData.gameId(), saveGameData.gameDataJSON());
    }
}
