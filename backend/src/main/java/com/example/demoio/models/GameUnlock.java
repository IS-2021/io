package com.example.demoio.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "GameUnlock")
@Getter
@Setter
@NoArgsConstructor
public class GameUnlock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unlockId")
    @Setter(AccessLevel.NONE)
    private Long unlockId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "gameId")
    private Game game;

    @Column(name = "completionTimestamp")
    private LocalDateTime completionTimestamp;

    @Column(name = "dailyTasksDate")
    private LocalDate dailyTasksDate;
}
