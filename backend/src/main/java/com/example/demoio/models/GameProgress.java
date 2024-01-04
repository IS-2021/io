package com.example.demoio.models;


import jakarta.persistence.*;
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
    private int bestScore;

    @ManyToOne()
    @JoinColumn(name = "user_fk")
    private User user;

    public GameProgress(int game_id, int bestScore) {
        this.game_id = game_id;
        this.bestScore = bestScore;
    }
}
