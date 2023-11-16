package com.example.projectaandmsystem.domain.team.service;

import com.example.projectaandmsystem.domain.team.entity.TeamMember;
import com.example.projectaandmsystem.domain.team.repository.TeamMemberRepository;
import com.example.projectaandmsystem.domain.team.repository.TeamRepository;
import com.example.projectaandmsystem.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TeamAcceptService {

    private final TeamRepository teamRepo;
    private final TeamMemberRepository teamMemberRepo;

    @Transactional
    public void accept(long teamId,
                       long teamMemberId) {

        if(teamRepo.findById(teamId).isEmpty())
            throw new EntityNotFoundException("해당 팀을 찾을 수 없음");

        TeamMember teamMember = teamMemberRepo.findById(teamMemberId)
                .orElseThrow(() -> new EntityNotFoundException("해당 팀원을 찾을 수 없음"));

        teamMember.accept();

    }

}