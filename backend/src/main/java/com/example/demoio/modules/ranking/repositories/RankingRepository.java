package com.example.demoio.modules.ranking.repositories;

import com.example.demoio.models.Game;
import com.example.demoio.models.Ranking;
import com.example.demoio.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RankingRepository extends JpaRepository<Ranking, Long> {
    List<Ranking> findAllByGameGameId(Long gameId);

    Optional<Ranking> findByGame_GameIdAndUserUserId(Long gameId, Long userId);

    Ranking findByGameAndUser(Game game, User user);

    List<Ranking> findAllByUserUserId(Long userId);

    default Integer sumAllScoresByUserId(Long userId) {
        List<Ranking> rankings = findAllByUserUserId(userId);

        return rankings.stream()
                .mapToInt(Ranking::getBestScore)
                .sum();
    }
}
