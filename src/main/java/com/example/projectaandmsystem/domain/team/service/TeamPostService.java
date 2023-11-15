package com.example.projectaandmsystem.domain.team.service;

import com.example.projectaandmsystem.domain.account.entity.Account;
import com.example.projectaandmsystem.domain.account.repository.AccountRepository;
import com.example.projectaandmsystem.domain.team.dto.TeamPostDto;
import com.example.projectaandmsystem.domain.team.entity.Team;
import com.example.projectaandmsystem.domain.team.entity.TeamMember;
import com.example.projectaandmsystem.domain.team.repository.TeamMemberRepository;
import com.example.projectaandmsystem.domain.team.repository.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TeamPostService {

    private final TeamRepository teamRepo;
    private final TeamMemberRepository teamMemberRepo;
    private final AccountRepository accountRepo;

    @Transactional
    public TeamPostDto.Response createTeam(TeamPostDto.Request request) {

        Account account = accountRepo.findByEmail(request.getTeamLeaderEmail())
                .orElseThrow(() -> new EntityNotFoundException("해당하는 이메일의 사용자 존재하지 않음"));

        Team team = teamRepo.save(request.toEntity());

        teamMemberRepo.save(TeamMember.createTeamLeader(account, team));

        return TeamPostDto.Response.from(team);
    }

}
