package com.example.demoio.modules.games.services;

import com.example.demoio.core.auth.services.UserProvider;
import com.example.demoio.models.Game;
import com.example.demoio.models.GameUnlock;
import com.example.demoio.modules.games.repositories.GameUnlockRepository;
import org.springframework.stereotype.Service;

@Service
public class GameUnlockService {
    private final GameUnlockRepository gameUnlockRepository;
    private final UserProvider userProvider;

    public GameUnlockService(GameUnlockRepository gameUnlockRepository, UserProvider userProvider) {
        this.gameUnlockRepository = gameUnlockRepository;
        this.userProvider = userProvider;
    }

    public void unlockGame(Game game) {
        GameUnlock gameUnlock = new GameUnlock(this.userProvider.getCurrentUser(), game);
        this.gameUnlockRepository.save(gameUnlock);
    }
}
