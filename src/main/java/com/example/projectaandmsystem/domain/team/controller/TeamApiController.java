package com.example.projectaandmsystem.domain.team.controller;

import com.example.projectaandmsystem.domain.team.dto.TeamMemberInviteDto;
import com.example.projectaandmsystem.domain.team.dto.TeamPostDto;
import com.example.projectaandmsystem.domain.team.service.TeamAcceptService;
import com.example.projectaandmsystem.domain.team.service.TeamInviteService;
import com.example.projectaandmsystem.domain.team.service.TeamPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/teams")
@RestController
public class TeamApiController {

    private final TeamPostService teamPostService;
    private final TeamInviteService teamInviteService;
    private final TeamAcceptService teamAcceptService;

    @PostMapping
    public ResponseEntity<TeamPostDto.Response> createTeam(@RequestBody @Valid TeamPostDto.Request request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teamPostService.createTeam(request));
    }

    @PostMapping("/{teamId}/team-members")
    public ResponseEntity<TeamMemberInviteDto.Response> inviteTeamMember(@PathVariable("teamId") long teamId,
                                                                         @RequestBody @Valid TeamMemberInviteDto.Request request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teamInviteService.invite(teamId, request));
    }

    @PatchMapping("/{teamId}/team-members/{id}/accept")
    public ResponseEntity<TeamMemberInviteDto.Response> acceptTeam(@PathVariable("teamId") long teamId,
                                                                   @PathVariable("id") long memberId) {
        teamAcceptService.accept(teamId, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
