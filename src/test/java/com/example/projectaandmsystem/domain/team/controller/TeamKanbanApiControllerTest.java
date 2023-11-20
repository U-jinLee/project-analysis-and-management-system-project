package com.example.projectaandmsystem.domain.team.controller;

import com.example.projectaandmsystem.IntegrationTest;
import com.example.projectaandmsystem.domain.account.entity.Account;
import com.example.projectaandmsystem.domain.account.repository.AccountRepository;
import com.example.projectaandmsystem.domain.team.dto.TeamKanbanCreateDto;
import com.example.projectaandmsystem.domain.team.entity.Kanban;
import com.example.projectaandmsystem.domain.team.entity.Team;
import com.example.projectaandmsystem.domain.team.repository.KanBanRepository;
import com.example.projectaandmsystem.domain.team.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TeamKanbanApiControllerTest extends IntegrationTest {

    @Autowired
    AccountRepository accountRepo;
    @Autowired
    TeamRepository teamRepo;
    @Autowired
    KanBanRepository kanbanRepo;

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

    //Postman 중복 검사
    @Test
    void 칸반_불러오기_성공() throws Exception {

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

        kanbanRepo.save(Kanban
                .builder()
                        .name("To do")
                        .rowNumber(0)
                        .team(team)
                .build());

        kanbanRepo.save(Kanban
                .builder()
                .name("Progress")
                .rowNumber(1)
                .team(team)
                .build());

        mvc.perform(get("/api/teams/{teamId}/kanbans", team.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void 칸반_지우기_성공() throws Exception {
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

        Kanban kanban = kanbanRepo.save(Kanban
                .builder()
                .name("To do")
                .rowNumber(0)
                .team(team)
                .build());


        mvc.perform(delete("/api/teams/{teamId}/kanbans/{id}", team.getId(), kanban.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

    }

}