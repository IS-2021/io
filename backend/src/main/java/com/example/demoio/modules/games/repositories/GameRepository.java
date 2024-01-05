package com.example.demoio.modules.games.repositories;

import com.example.demoio.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
    Game findByGameId(Long gameId);
}
