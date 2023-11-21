package com.example.projectaandmsystem.domain.team.entity;

import com.example.projectaandmsystem.domain.team.dto.TeamKanbanUpdateDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Kanban")
@Entity
public class Kanban {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "row_number")
    private Integer rowNumber;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "kanban", cascade = CascadeType.ALL)
    private List<Ticket> tickets = new ArrayList<>();

    @Builder
    public Kanban(String name, Integer rowNumber, Team team) {
        this.name = name;
        this.rowNumber = rowNumber;
        this.team = team;
    }

    public void changeKanbanNameTo(TeamKanbanUpdateDto.Request request) {
        this.name = request.getName();
    }

    public void swapRowNumberTo(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }
}