package com.example.projectaandmsystem.domain.team.entity;

import com.example.projectaandmsystem.domain.account.entity.Account;
import com.example.projectaandmsystem.domain.team.model.Status;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "team_member")
@Entity
public class TeamMember {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @Builder
    public TeamMember(Status status, Account account, Team team) {
        this.status = status;
        this.account = account;
        this.team = team;
    }

    public static TeamMember createTeamLeader(Account account, Team team) {
        return TeamMember.builder()
                .status(Status.LEADER)
                .account(account)
                .team(team)
                .build();
    }

    public static TeamMember inviteTeamMember(Account account, Team team) {
        return TeamMember.builder()
                .status(Status.INVITE)
                .account(account)
                .team(team)
                .build();
    }

}