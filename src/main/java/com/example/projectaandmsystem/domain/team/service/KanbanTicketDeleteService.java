package com.example.projectaandmsystem.domain.team.service;

import com.example.projectaandmsystem.domain.team.entity.Kanban;
import com.example.projectaandmsystem.domain.team.entity.Ticket;
import com.example.projectaandmsystem.domain.team.exception.CantDeleteTicketException;
import com.example.projectaandmsystem.domain.team.repository.KanBanRepository;
import com.example.projectaandmsystem.domain.team.repository.TicketRepository;
import com.example.projectaandmsystem.global.error.exception.EntityNotFoundException;
import com.example.projectaandmsystem.global.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class KanbanTicketDeleteService {

    private final KanBanRepository kanbanRepo;
    private final TicketRepository ticketRepo;

    private final JwtUtil jwtUtil;

    @Transactional
    public void delete(String accessToken, long kanbanId, long ticketId) {

        String userEmail = jwtUtil.parseClaims(accessToken.substring(7))
                .getSubject();

        Ticket ticket = ticketRepo.findById(ticketId).orElseThrow(() ->
                new EntityNotFoundException("존재하지 않는 티켓:: " + ticketId));

        Kanban kanban = kanbanRepo.findById(kanbanId).orElseThrow(() ->
                new EntityNotFoundException("존재하지 않는 칸반:: " + kanbanId));

        verifyTicketDeletionPermission(kanban, userEmail, ticket);

        ticketRepo.delete(ticket);

    }

    private void verifyTicketDeletionPermission(Kanban kanban, String userEmail, Ticket ticket) {
        if (!kanban.getTeam().getTeamLeaderEmail().equals(userEmail) ||
            !ticket.getTeamMember().getAccount().getEmail().equals(userEmail))
            throw new CantDeleteTicketException();
    }

}