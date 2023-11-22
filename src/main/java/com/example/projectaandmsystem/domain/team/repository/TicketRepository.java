package com.example.projectaandmsystem.domain.team.repository;

import com.example.projectaandmsystem.domain.team.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}