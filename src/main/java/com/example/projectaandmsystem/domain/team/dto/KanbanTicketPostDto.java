package com.example.projectaandmsystem.domain.team.dto;

import com.example.projectaandmsystem.domain.team.entity.Kanban;
import com.example.projectaandmsystem.domain.team.entity.Team;
import com.example.projectaandmsystem.domain.team.entity.TeamMember;
import com.example.projectaandmsystem.domain.team.entity.Ticket;
import com.example.projectaandmsystem.domain.team.model.Tag;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KanbanTicketPostDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Request {

        @NotEmpty
        private String name;
        @NotNull
        private Tag tag;
        @NotNull
        private Double amountOfWork;

        private LocalDateTime deadline;

        private Integer rowNumber;


        public static Request from(String name, Tag tag, double amountOfWork, LocalDateTime deadline, int rowNumber) {
            return new Request(name, tag, amountOfWork, deadline, rowNumber);
        }

        public Ticket toEntity(Kanban kanban, TeamMember teamMember) {
            return Ticket.builder()
                    .name(this.name)
                    .tag(this.tag)
                    .amountOfWork(this.amountOfWork)
                    .deadline(this.deadline)
                    .rowNumber(this.rowNumber)
                    .kanban(kanban)
                    .teamMember(teamMember)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {

        private long id;
        private String teamName;
        private String teamLeaderEmail;
        private String description;
        private List<TeamGetKanbansDto.Response.KanbanVo> kanbans = new ArrayList<>();

        public static KanbanTicketPostDto.Response from(Team team) {

            List<TeamGetKanbansDto.Response.KanbanVo> kanbans = team.getKanbans().stream().map(TeamGetKanbansDto.Response.KanbanVo::from)
                    .sorted(Comparator.comparingInt(TeamGetKanbansDto.Response.KanbanVo::getRowNumber))
                    .toList();

            return new KanbanTicketPostDto.Response(
                    team.getId(),
                    team.getName(),
                    team.getTeamLeaderEmail(),
                    team.getDescription(),
                    kanbans);
        }

        @Getter
        @NoArgsConstructor(access = AccessLevel.PROTECTED)
        @AllArgsConstructor(access = AccessLevel.PRIVATE)
        public static class KanbanVo {
            private String kanbanName;
            private int rowNumber;
            private List<TeamGetKanbansDto.Response.KanbanVo.TicketVo> tickets = new ArrayList<>();

            public static KanbanTicketPostDto.Response.KanbanVo from(Kanban kanban) {

                List<TeamGetKanbansDto.Response.KanbanVo.TicketVo> tickets = kanban.getTickets()
                        .stream().map(TeamGetKanbansDto.Response.KanbanVo.TicketVo::from)
                        .sorted(Comparator.comparingInt(TeamGetKanbansDto.Response.KanbanVo.TicketVo::getRowNumber))
                        .toList();

                return new KanbanTicketPostDto.Response.KanbanVo(
                        kanban.getName(),
                        kanban.getRowNumber(),
                        tickets
                );

            }

            @Getter
            @NoArgsConstructor(access = AccessLevel.PROTECTED)
            @AllArgsConstructor(access = AccessLevel.PRIVATE)
            public static class TicketVo {
                private String name;
                private Tag tag;
                private double amountOfWork;
                @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
                private LocalDateTime deadline;
                private int rowNumber;
                private String teamMemberEmail;

                public static KanbanTicketPostDto.Response.KanbanVo.TicketVo from(Ticket ticket) {
                    return new KanbanTicketPostDto.Response.KanbanVo.TicketVo(
                            ticket.getName(),
                            ticket.getTag(),
                            ticket.getAmountOfWork(),
                            ticket.getDeadline(),
                            ticket.getRowNumber(),
                            ticket.getTeamMember().getAccount().getEmail()
                    );
                }

            }

        }

    }


}
