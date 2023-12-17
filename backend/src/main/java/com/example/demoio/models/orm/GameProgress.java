package com.example.demoio.models.orm;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class GameProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int game_id;
    private String username;
    private int bestScore;

    public GameProgress(int game_id, String username, int bestScore) {
        this.game_id = game_id;
        this.username = username;
        this.bestScore = bestScore;
    }
}
