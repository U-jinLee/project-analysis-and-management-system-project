package com.example.projectaandmsystem.domain.team.dto;

import com.example.projectaandmsystem.domain.team.entity.Kanban;
import com.example.projectaandmsystem.domain.team.entity.Team;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TeamKanbanCreateDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Request {

        @NotEmpty
        private String name;

        @NotNull
        private Integer rowNumber;

        public static Request from(String name, int rowNumber) {
            return new Request(name, rowNumber);
        }

        public Kanban toEntity(Team team) {
            return Kanban.builder()
                    .name(name)
                    .rowNumber(rowNumber)
                    .team(team)
                    .build();
        }

    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {

        private long id;
        private String columnName;
        private int rowNumber;
        private Team team;

        public static Response from(Kanban kanban) {
            return new Response(
                    kanban.getId(),
                    kanban.getName(),
                    kanban.getRowNumber(),
                    kanban.getTeam());
        }
    }

}
