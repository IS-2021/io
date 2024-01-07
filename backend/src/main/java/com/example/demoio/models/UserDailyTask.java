package com.example.demoio.models;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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

    @Column(name = "isCompleted", columnDefinition = "boolean default false")
    private Boolean isCompleted;

    @Column(name = "takenDate")
    private LocalDate takenDate;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "dailyTaskId")
    private DailyTask dailyTask;

    public UserDailyTask(User user, DailyTask dailyTask) {
        this.user = user;
        this.dailyTask = dailyTask;
        this.isCompleted = false;
        this.takenDate = LocalDate.now();
    }
}