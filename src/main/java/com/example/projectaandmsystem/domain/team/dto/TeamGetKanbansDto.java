package com.example.projectaandmsystem.domain.team.dto;

import com.example.projectaandmsystem.domain.team.entity.Kanban;
import com.example.projectaandmsystem.domain.team.entity.Team;
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
public class TeamGetKanbansDto {

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
        private String teamName;
        private String teamLeaderEmail;
        private String description;
        private List<KanbanVo> kanbans = new ArrayList<>();

        public static Response from(Team team) {

            List<KanbanVo> kanbans = team.getKanbans().stream().map(KanbanVo::from)
                    .sorted(Comparator.comparingInt(KanbanVo::getRowNumber))
                    .toList();

            return new Response(
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
            private List<TicketVo> tickets = new ArrayList<>();

            public static KanbanVo from(Kanban kanban) {

                List<TicketVo> tickets = kanban.getTickets()
                        .stream().map(TicketVo::from)
                        .sorted(Comparator.comparingInt(TicketVo::getRowNumber))
                        .toList();

                return new KanbanVo(
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

                public static TicketVo from(Ticket ticket) {
                    return new TicketVo(
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
