package com.example.demoio.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "DailyTask")
@Getter
@Setter
@NoArgsConstructor
public class DailyTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dailyTaskId")
    @Setter(AccessLevel.NONE)
    private Long dailyTaskId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "gameId")
    private Game game;

    @Column(name = "coinsReward")
    private Integer coinsReward;

    @Column(name = "date")
    private LocalDate date;
}
