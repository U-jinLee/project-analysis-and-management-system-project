package com.example.projectaandmsystem.domain.team.controller;

import com.example.projectaandmsystem.IntegrationTest;
import com.example.projectaandmsystem.domain.account.entity.Account;
import com.example.projectaandmsystem.domain.account.repository.AccountRepository;
import com.example.projectaandmsystem.domain.team.dto.TeamKanbanCreateDto;
import com.example.projectaandmsystem.domain.team.entity.Team;
import com.example.projectaandmsystem.domain.team.entity.TeamMember;
import com.example.projectaandmsystem.domain.team.model.Status;
import com.example.projectaandmsystem.domain.team.repository.TeamRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TeamKanbanApiControllerTest extends IntegrationTest {

    @Autowired
    TeamRepository teamRepo;
    @Autowired
    AccountRepository accountRepo;

    @Test
    void 칸반_추가_성공() throws Exception {

        Account account = accountRepo.save(
                Account.builder()
                        .email("yoojinlee.dev@gmail.com")
                        .name("이유진")
                        .password("1q2w3e4r!")
                        .build()
        );

        Team team = teamRepo.save(Team.builder()
                .name("Test team")
                .teamLeaderEmail(account.getEmail())
                .description("Team make test")
                .build());

        TeamKanbanCreateDto.Request request = TeamKanbanCreateDto.Request.from("Test column", 1);

        mvc.perform(post("/api/teams/{teamId}/kanbans", team.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated());

    }
}