package com.example.projectaandmsystem.domain.team.service;

import com.example.projectaandmsystem.domain.team.dto.TeamGetKanbansDto;
import com.example.projectaandmsystem.domain.team.entity.Team;
import com.example.projectaandmsystem.domain.team.repository.TeamRepository;
import com.example.projectaandmsystem.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class TeamKanbanQueryService {
    private final TeamRepository teamRepo;

    public TeamGetKanbansDto.Response getKanbans(Long teamId) {

        Team team = teamRepo.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("해당하는 팀을 찾을 수 없습니다"));

        return TeamGetKanbansDto.Response.from(team);
    }
}
