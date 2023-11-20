package com.example.projectaandmsystem.domain.team.service;

import com.example.projectaandmsystem.domain.team.dto.TeamKanbanRowNumberUpdateDto;
import com.example.projectaandmsystem.domain.team.dto.TeamKanbanUpdateDto;
import com.example.projectaandmsystem.domain.team.entity.Kanban;
import com.example.projectaandmsystem.domain.team.entity.Team;
import com.example.projectaandmsystem.domain.team.exception.RowNumberOverException;
import com.example.projectaandmsystem.domain.team.repository.KanBanRepository;
import com.example.projectaandmsystem.domain.team.repository.TeamRepository;
import com.example.projectaandmsystem.global.error.exception.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TeamKanbanUpdateService {

    private static final String TEAM_NOT_FOUND_EXCEPTION_MESSAGE = "해당하는 팀을 찾을 수 없습니다";
    private static final String KANBAN_NOT_FOUND_EXCEPTION_MESSAGE = "해당하는 칸반을 찾을 수 없습니다";

    private final TeamRepository teamRepo;
    private final KanBanRepository kanbanRepo;

    @Transactional
    public void updateName(long teamId, long kanbanId, TeamKanbanUpdateDto.@Valid Request request) {

        if(teamRepo.findById(teamId).isEmpty()) throw new EntityNotFoundException(TEAM_NOT_FOUND_EXCEPTION_MESSAGE);

        Kanban kanban = kanbanRepo.findById(kanbanId).orElseThrow(() ->
                new EntityNotFoundException(KANBAN_NOT_FOUND_EXCEPTION_MESSAGE));

        kanban.changeKanbanNameTo(request);
    }

    @Transactional
    public void updateRowNumber(long teamId, long kanbanId, TeamKanbanRowNumberUpdateDto.Request request) {

        Team team = teamRepo.findById(teamId).orElseThrow(() ->
                new EntityNotFoundException(TEAM_NOT_FOUND_EXCEPTION_MESSAGE));

        Kanban kanban = kanbanRepo.findById(kanbanId).orElseThrow(() ->
                new EntityNotFoundException(KANBAN_NOT_FOUND_EXCEPTION_MESSAGE));
        
        List<Kanban> kanbans = team.getKanbans();

        Integer originalRowNumber = kanban.getRowNumber();
        Integer willChangeRowNumber = request.getRowNumber();

        log.info("Kanban's size::{}",kanbans.size());
        if(kanbans.size() < willChangeRowNumber) throw new RowNumberOverException();

        Kanban willChangeKanban = kanbans.stream().filter(k ->
                        k.getRowNumber().equals(willChangeRowNumber)).findFirst()
                .orElseThrow(() -> new EntityNotFoundException(KANBAN_NOT_FOUND_EXCEPTION_MESSAGE));

        kanban.swapRowNumberTo(willChangeRowNumber);
        willChangeKanban.swapRowNumberTo(originalRowNumber);

    }
}