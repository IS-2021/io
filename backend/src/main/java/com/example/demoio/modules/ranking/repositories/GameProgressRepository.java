package com.example.demoio.modules.ranking.repositories;

import com.example.demoio.models.GameProgress;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameProgressRepository extends CrudRepository<GameProgress, Long> {

    @Query("SELECT gp FROM GameProgress gp WHERE gp.game_id = :gameID ORDER BY gp.bestScore DESC")
    List<GameProgress> getProgressByGameId(@Param("gameID") int gameID, Pageable pageable);

    @Query("SELECT gp FROM GameProgress gp WHERE gp.game_id = :gameID AND gp.user.id = :userID")
    GameProgress getUserProgressByGameId(@Param("gameID") int gameID, @Param("userID") int userID);
}
