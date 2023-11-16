package com.example.projectaandmsystem.domain.team.service;

import com.example.projectaandmsystem.domain.team.dto.TeamKanbanCreateDto;
import com.example.projectaandmsystem.domain.team.entity.Team;
import com.example.projectaandmsystem.domain.team.repository.KanBanRepository;
import com.example.projectaandmsystem.domain.team.repository.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TeamColumnCreateService {

    private final TeamRepository teamRepo;
    private final KanBanRepository kanbanRepo;

    @Transactional
    public TeamKanbanCreateDto.Response create(long teamId, TeamKanbanCreateDto.Request request) {
        Team team = teamRepo.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 팀입니다"));

        return TeamKanbanCreateDto.Response.from(kanbanRepo.save(request.toEntity(team)));
    }

}
