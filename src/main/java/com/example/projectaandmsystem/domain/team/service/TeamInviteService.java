package com.example.projectaandmsystem.domain.team.service;

import com.example.projectaandmsystem.domain.account.entity.Account;
import com.example.projectaandmsystem.domain.account.repository.AccountRepository;
import com.example.projectaandmsystem.domain.team.dto.TeamMemberInviteDto;
import com.example.projectaandmsystem.domain.team.entity.Team;
import com.example.projectaandmsystem.domain.team.entity.TeamMember;
import com.example.projectaandmsystem.domain.team.exception.AlreadyInvitedMemberException;
import com.example.projectaandmsystem.domain.team.repository.TeamMemberRepository;
import com.example.projectaandmsystem.domain.team.repository.TeamRepository;
import com.example.projectaandmsystem.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TeamInviteService {
    private final TeamRepository teamRepo;
    private final AccountRepository accountRepo;
    private final TeamMemberRepository teamMemberRepo;

    public TeamMemberInviteDto.Response invite(long teamId, TeamMemberInviteDto.Request request) {

        Account account = accountRepo.findByEmail(request.getMemberEmail())
                .orElseThrow(() -> new EntityNotFoundException("해당 이메일을 가진 계정을 찾을 수 없음"));

        Team team = teamRepo.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("해당 팀을 찾을 수 없음"));

        // 이미 팀에 있는지 확인
        if(teamMemberRepo.findByAccountAndTeam(account, team).isPresent())
            throw new AlreadyInvitedMemberException();

        return TeamMemberInviteDto.Response.from(teamMemberRepo.save(TeamMember.inviteTeamMember(account, team)));
    }
}
