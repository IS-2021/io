package com.example.demoio.modules.games.services;

import com.example.demoio.models.GameUnlock;
import com.example.demoio.models.User;
import com.example.demoio.modules.games.repositories.GameUnlockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final GameUnlockRepository gameUnlockRepository;

    public GameService(GameUnlockRepository gameUnlockRepository) {
        this.gameUnlockRepository = gameUnlockRepository;
    }

    public List<GameUnlock> getPassedGamesForUser(User user) {
        return gameUnlockRepository.findByUser(user);
    }

}
