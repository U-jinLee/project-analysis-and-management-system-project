package com.example.projectaandmsystem.domain.team.service;

import com.example.projectaandmsystem.domain.team.entity.Kanban;
import com.example.projectaandmsystem.domain.team.exception.TicketExistException;
import com.example.projectaandmsystem.domain.team.repository.KanBanRepository;
import com.example.projectaandmsystem.domain.team.repository.TeamRepository;
import com.example.projectaandmsystem.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TeamKanbanDeleteService {

    private final TeamRepository teamRepo;
    private final KanBanRepository kanbanRepo;

    @Transactional
    public void delete(long teamId, long kanbanId) {

        if (teamRepo.findById(teamId).isEmpty()) throw new EntityNotFoundException("해당하는 팀을 찾을 수 없습니다");
        
        Kanban kanban = kanbanRepo.findById(kanbanId).orElseThrow(() ->
                new EntityNotFoundException("해당하는 칸반을 찾을 수 없습니다"));

        validateTicketExistence(kanban);

        kanbanRepo.delete(kanban);
    }

    private void validateTicketExistence(Kanban kanban) {
        if(!kanban.getTickets().isEmpty()) throw new TicketExistException();
    }

}