package com.example.demoio.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "User")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    @Setter(AccessLevel.NONE)
    private Long userId;

    @Column(name = "coins", nullable = false, columnDefinition = "int default 0")
    private int userCoins;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "lastBonusClaimedOn")
//    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lastBonusClaimedOn;

    @Column(name = "claimedBonusCount", nullable = false, columnDefinition = "int default 1")
    private int claimedBonusCount;

    @Column(name = "extraPoints", nullable = false, columnDefinition = "int default 0")
    private int extraPoints;

    @OneToMany(mappedBy = "user")
    private List<Ranking> rankings;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean isEligibleForBonus() {
        if (this.lastBonusClaimedOn == null) {
            return true;
        }

        LocalDateTime nextDay = this.lastBonusClaimedOn.plusDays(1).truncatedTo(java.time.temporal.ChronoUnit.DAYS);
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(nextDay) || now.isEqual(nextDay);
    }
}
