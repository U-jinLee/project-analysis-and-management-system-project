package com.example.projectaandmsystem.domain.team.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @OneToMany(mappedBy = "team")
    private List<Kanban> kanbans = new ArrayList<>();

    @Builder
    public Team(String name, String teamLeaderEmail, String description) {
        this.name = name;
        this.teamLeaderEmail = teamLeaderEmail;
        this.description = description;
    }
}
