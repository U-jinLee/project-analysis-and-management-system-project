package com.example.projectaandmsystem.domain.team.dto;

import com.example.projectaandmsystem.domain.team.entity.Team;
import com.example.projectaandmsystem.domain.team.entity.TeamMember;
import com.example.projectaandmsystem.domain.team.model.Status;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TeamMemberInviteDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Request {

        @Email
        @NotEmpty
        private String memberEmail;

        public static Request from(String memberEmail) {
            return new Request(memberEmail);
        }

    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {

        private long id;
        private String memberName;
        private String memberEmail;
        private Status status;
        private Team team;

        public static Response from(TeamMember member) {
            return new Response(
                    member.getId(),
                    member.getAccount().getName(),
                    member.getAccount().getEmail(),
                    member.getStatus(),
                    member.getTeam());
        }

    }

}
