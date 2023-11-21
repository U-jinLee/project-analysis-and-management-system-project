package com.example.projectaandmsystem.domain.team.controller;

import com.example.projectaandmsystem.domain.team.dto.KanbanTicketPostDto;
import com.example.projectaandmsystem.domain.team.dto.KanbanTicketUpdateDto;
import com.example.projectaandmsystem.domain.team.service.KanbanTicketPostService;
import com.example.projectaandmsystem.domain.team.service.KanbanTicketUpdateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/kanbans/{kanbanId}/tickets")
@RestController
public class KanbanTicketApiController {

    private final KanbanTicketPostService ticketPostService;
    private final KanbanTicketUpdateService ticketUpdateService;

    @PostMapping
    public ResponseEntity<KanbanTicketPostDto.Response> postTicket(@RequestHeader("Authorization") String accessToken,
                                                                   @PathVariable("kanbanId") long kanbanId,
                                                                   @RequestBody @Valid KanbanTicketPostDto.Request request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketPostService.post(accessToken, kanbanId, request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putTicket(@RequestHeader("Authorization") String accessToken,
                                            @PathVariable("kanbanId") long kanbanId,
                                            @PathVariable("id") long ticketId,
                                            @RequestBody @Valid KanbanTicketUpdateDto.Request request) {
        ticketUpdateService.put(accessToken, kanbanId, ticketId, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }


}
