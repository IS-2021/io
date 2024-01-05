package com.example.demoio.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Ranking")
public class Ranking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rankingId")
    @Setter(AccessLevel.NONE)
    private Long rankingId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "gameId")
    private Game game;

    @Column(name = "bestScore", nullable = false, columnDefinition = "int default 0")
    private int bestScore;

    public Ranking(User user, Game game, int bestScore) {
        this.user = user;
        this.game = game;
        this.bestScore = bestScore;
    }
}
