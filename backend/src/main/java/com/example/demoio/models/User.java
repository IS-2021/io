package com.example.demoio.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;
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

    @Column(name = "lastLoggedIn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoggedIn;

    @Column(name = "daysLoggedIn", nullable = false, columnDefinition = "int default 1")
    private int daysLoggedIn;

    @OneToMany(mappedBy = "user")
    private List<Ranking> rankings;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
