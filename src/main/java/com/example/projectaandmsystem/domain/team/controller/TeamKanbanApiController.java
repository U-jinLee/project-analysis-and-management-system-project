package com.example.projectaandmsystem.domain.team.controller;

import com.example.projectaandmsystem.domain.team.dto.TeamGetKanbansDto;
import com.example.projectaandmsystem.domain.team.dto.TeamKanbanCreateDto;
import com.example.projectaandmsystem.domain.team.service.TeamColumnCreateService;
import com.example.projectaandmsystem.domain.team.service.TeamKanbanQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/teams/{teamId}/kanbans")
@RestController
public class TeamKanbanApiController {

    private final TeamColumnCreateService teamColumnCreateService;
    private final TeamKanbanQueryService teamKanbanQueryService;

    @PostMapping
    public ResponseEntity<TeamKanbanCreateDto.Response> createKanban(@PathVariable(name = "teamId") Long teamId,
                                                                     @RequestBody @Valid TeamKanbanCreateDto.Request request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teamColumnCreateService.create(teamId, request));
    }

    @GetMapping
    public ResponseEntity<TeamGetKanbansDto.Response> getKanbans(@PathVariable(name = "teamId") Long teamId) {
        return ResponseEntity.status(HttpStatus.OK).body(teamKanbanQueryService.getKanbans(teamId));
    }

}
