package com.example.demoio.modules.datastorage.services;

import com.example.demoio.core.auth.services.UserProvider;
import com.example.demoio.models.Game;
import com.example.demoio.models.GameData;
import com.example.demoio.modules.datastorage.repositories.GameDataRepository;
import com.example.demoio.modules.games.repositories.GameRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DataStorageService {
    private final GameDataRepository gameDataRepository;
    private final GameRepository gameRepository;
    private final UserProvider userProvider;


    public DataStorageService(GameDataRepository gameDataRepository, GameRepository gameRepository, UserProvider userProvider) {
        this.gameDataRepository = gameDataRepository;
        this.gameRepository = gameRepository;
        this.userProvider = userProvider;
    }

    public Optional<String> getCurrentUserGameData(Long gameId) {
        Optional<GameData> gameData = gameDataRepository.findByGame_GameIdAndUserUserId(gameId, userProvider.getCurrentUserId());

        if (gameData.isPresent()) {
            return Optional.of(gameData.get().getGameData());
        } else {
            return Optional.ofNullable(null);
        }
    }

    public void saveGameData(Long gameId, String gameDataJSON) {
        Game game = gameRepository.findByGameId(gameId);
        Optional<GameData> gameData = gameDataRepository.findByGame_GameIdAndUserUserId(gameId, userProvider.getCurrentUserId());

        if (gameData.isPresent()) {
            gameData.get().setGameData(gameDataJSON);
            gameDataRepository.save(gameData.get());
        } else {
            GameData newGameData = new GameData(game, userProvider.getCurrentUser(), gameDataJSON);
            gameDataRepository.save(newGameData);
        }
    }
}
