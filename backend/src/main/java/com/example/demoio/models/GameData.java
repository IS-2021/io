package com.example.demoio.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "GameData")
@Getter
@Setter
@NoArgsConstructor
public class GameData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gameDataId")
    @Setter(AccessLevel.NONE)
    private Long gameDataId;

    @ManyToOne
    @JoinColumn(name = "gameId")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(name = "gameData")
    private String gameData;

    public GameData(Game game, User user, String gameData) {
        this.game = game;
        this.user = user;
        this.gameData = gameData;
    }
}