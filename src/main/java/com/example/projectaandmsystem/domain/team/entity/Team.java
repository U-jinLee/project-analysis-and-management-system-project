package com.example.projectaandmsystem.domain.team.entity;

import jakarta.persistence.*;

@Table(name = "team")
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "team_leader_email")
    private String teamLeaderEmail;

    @Column(name = "description")
    private String description;

}
