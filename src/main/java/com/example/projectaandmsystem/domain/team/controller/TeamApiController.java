package com.example.projectaandmsystem.domain.team.controller;

import com.example.projectaandmsystem.domain.team.dto.TeamPostDto;
import com.example.projectaandmsystem.domain.team.service.TeamPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/teams")
@RestController
public class TeamApiController {

    private final TeamPostService teamPostService;

    @PostMapping
    public ResponseEntity<TeamPostDto.Response> createTeam(@RequestBody @Valid TeamPostDto.Request request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teamPostService.createTeam(request));
    }

}
