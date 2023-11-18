package com.example.projectaandmsystem.domain.team.entity;

import com.example.projectaandmsystem.domain.team.model.Tag;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ticket")
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    private Tag tag;

    @Column(name = "amount_of_work")
    private Double amountOfWork;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @Column(name = "row_number")
    private Integer rowNumber;

    @ManyToOne
    @JoinColumn(name = "kanban_id")
    private Kanban kanban;

    @ManyToOne
    @JoinColumn(name = "team_member_id")
    private TeamMember teamMember;

    @Builder
    public Ticket(String name,
                  Tag tag,
                  double amountOfWork,
                  LocalDateTime deadline,
                  int rowNumber,
                  Kanban kanban,
                  TeamMember teamMember) {
        this.name = name;
        this.tag = tag;
        this.amountOfWork = amountOfWork;
        this.deadline = deadline;
        this.rowNumber = rowNumber;
        this.kanban = kanban;
        this.teamMember = teamMember;
    }

}
