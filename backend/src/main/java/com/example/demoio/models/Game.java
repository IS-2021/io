package com.example.demoio.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Game")
@Getter
@Setter
@NoArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gameId")
    @Setter(AccessLevel.NONE)
    private Long gameId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false, length = 1000)
    private String description;

    @Column(name = "iframeURL")
    private String iframeURL;

    @Column(name = "frameWidth", columnDefinition = "int default 1280")
    private Integer frameWidth;

    @Column(name = "frameHeight", columnDefinition = "int default 720")
    private Integer frameHeight;

    @Column(name = "imageSlugName")
    private String imageSlugName;
}
