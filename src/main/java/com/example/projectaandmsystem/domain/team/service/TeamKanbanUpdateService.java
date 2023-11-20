package com.example.projectaandmsystem.domain.team.service;

import com.example.projectaandmsystem.domain.team.dto.TeamKanbanUpdateDto;
import com.example.projectaandmsystem.domain.team.entity.Kanban;
import com.example.projectaandmsystem.domain.team.repository.KanBanRepository;
import com.example.projectaandmsystem.domain.team.repository.TeamRepository;
import com.example.projectaandmsystem.global.error.exception.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TeamKanbanUpdateService {

    private final TeamRepository teamRepo;
    private final KanBanRepository kanbanRepo;

    @Transactional
    public void updateName(long teamId, long kanbanId, TeamKanbanUpdateDto.@Valid Request request) {

        if(teamRepo.findById(teamId).isEmpty()) throw new EntityNotFoundException("해당하는 팀을 찾을 수 없습니다");

        Kanban kanban = kanbanRepo.findById(kanbanId).orElseThrow(() ->
                new EntityNotFoundException("해당하는 칸반을 찾을 수 없습니다"));

        kanban.changeKanbanNameTo(request);
    }

}