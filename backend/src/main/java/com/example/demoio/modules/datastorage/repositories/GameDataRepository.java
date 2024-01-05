package com.example.demoio.modules.datastorage.repositories;

import com.example.demoio.models.GameData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameDataRepository extends JpaRepository<GameData, Long> {
    Optional<GameData> findByGame_GameIdAndUserUserId(Long gameId, Long userId);
}
