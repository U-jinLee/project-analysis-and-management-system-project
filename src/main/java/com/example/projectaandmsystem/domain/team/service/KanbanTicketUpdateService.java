package com.example.projectaandmsystem.domain.team.service;

import com.example.projectaandmsystem.domain.account.entity.Account;
import com.example.projectaandmsystem.domain.account.repository.AccountRepository;
import com.example.projectaandmsystem.domain.team.dto.KanbanTicketUpdateDto;
import com.example.projectaandmsystem.domain.team.entity.Kanban;
import com.example.projectaandmsystem.domain.team.entity.TeamMember;
import com.example.projectaandmsystem.domain.team.entity.Ticket;
import com.example.projectaandmsystem.domain.team.repository.KanBanRepository;
import com.example.projectaandmsystem.domain.team.repository.TeamMemberRepository;
import com.example.projectaandmsystem.domain.team.repository.TicketRepository;
import com.example.projectaandmsystem.global.error.exception.EntityNotFoundException;
import com.example.projectaandmsystem.global.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class KanbanTicketUpdateService {

    private final AccountRepository accountRepo;
    private final KanBanRepository kanbanRepo;
    private final TeamMemberRepository teamMemberRepo;
    private final TicketRepository ticketRepo;
    private final JwtUtil jwtUtil;

    @Transactional
    public void put(String accessToken, long kanbanId, long ticketId, KanbanTicketUpdateDto.Request request) {

        String userEmail = jwtUtil.parseClaims(accessToken.substring(7))
                .getSubject();

        Account account = accountRepo.findByEmail(userEmail).orElseThrow(() ->
                new EntityNotFoundException("존재하지 않는 사용자"));

        Kanban kanban = kanbanRepo.findById(kanbanId).orElseThrow(() ->
                new EntityNotFoundException("존재하지 않는 칸반보드"));

        TeamMember teamMember = teamMemberRepo.findByAccountAndTeam(account, kanban.getTeam())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 팀원"));

        Ticket ticket = ticketRepo.findById(ticketId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 티켓"));

        ticket.update(request, teamMember);
    }

}