package com.example.demoio.models;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "UserDailyTask")
@Getter
@Setter
@NoArgsConstructor
public class UserDailyTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userDailyTaskId")
    @Setter(AccessLevel.NONE)
    private Long userDailyTaskId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "dailyTaskId")
    private DailyTask dailyTask;
}