package com.example.projectaandmsystem.domain.team.repository;

import com.example.projectaandmsystem.domain.account.entity.Account;
import com.example.projectaandmsystem.domain.team.entity.Team;
import com.example.projectaandmsystem.domain.team.entity.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
    Optional<TeamMember> findByAccountAndTeam(Account account, Team team);
}