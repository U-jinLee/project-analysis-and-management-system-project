package com.example.projectaandmsystem.domain.team.repository;

import com.example.projectaandmsystem.domain.team.entity.Kanban;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KanBanRepository extends JpaRepository<Kanban, Long> {}