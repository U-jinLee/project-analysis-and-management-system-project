package com.example.projectaandmsystem.domain.team.dto;

import com.example.projectaandmsystem.domain.team.entity.Team;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TeamPostDto {

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Request {

        @NotEmpty
        private String name;

        @Email
        @NotEmpty
        private String teamLeaderEmail;

        @NotEmpty
        private String description;

        public static Request from(String email, String teamLeaderEmail, String description) {
            return new Request(
                    email,
                    teamLeaderEmail,
                    description);
        }

        public Team toEntity() {
            return Team.builder()
                    .name(name)
                    .teamLeaderEmail(teamLeaderEmail)
                    .description(description)
                    .build();
        }

    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {

        private String name;

        private String teamLeaderEmail;

        private String description;

        public static Response from(Team team) {
            return new Response(
                    team.getName(),
                    team.getTeamLeaderEmail(),
                    team.getDescription());
        }

    }

}
