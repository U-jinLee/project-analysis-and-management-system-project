package com.example.projectaandmsystem.domain.team.controller;

import com.example.projectaandmsystem.IntegrationTest;
import com.example.projectaandmsystem.domain.account.entity.Account;
import com.example.projectaandmsystem.domain.account.repository.AccountRepository;
import com.example.projectaandmsystem.domain.team.dto.TeamMemberInviteDto;
import com.example.projectaandmsystem.domain.team.dto.TeamPostDto;
import com.example.projectaandmsystem.domain.team.entity.Team;
import com.example.projectaandmsystem.domain.team.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TeamApiControllerTest extends IntegrationTest {
    @Autowired
    AccountRepository accountRepo;
    @Autowired
    TeamRepository teamRepo;

    @Test
    void 팀_생성_성공() throws Exception {
        //given
        Account account = accountRepo.save(
                Account.builder()
                        .email("yoojinlee.dev@gmail.com")
                        .name("이유진")
                        .password("1q2w3e4r!")
                        .build()
        );

        TeamPostDto.Request request =
                TeamPostDto.Request.from("Test team", account.getEmail(), "Team make test");

        mvc.perform(post("/api/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void 팀_초대_성공() throws Exception {
        //given
        Account account = accountRepo.save(
                Account.builder()
                        .email("yoojinlee.dev@gmail.com")
                        .name("이유진")
                        .password("1q2w3e4r!")
                        .build()
        );

        Account account2 = accountRepo.save(
                Account.builder()
                        .email("lky@gmail.com")
                        .name("아기연")
                        .password("1q2w3e4r!")
                        .build()
        );

        Team team = teamRepo.save(Team.builder()
                .name("Test team")
                .teamLeaderEmail(account.getEmail())
                .description("Team make test")
                .build());

        TeamMemberInviteDto.Request request = TeamMemberInviteDto.Request.from(account2.getEmail());

        mvc.perform(post("/api/teams/{teamId}/team-members", team.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

}