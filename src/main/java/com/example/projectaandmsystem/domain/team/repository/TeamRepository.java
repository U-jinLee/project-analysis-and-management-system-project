package com.example.projectaandmsystem.domain.team.repository;

import com.example.projectaandmsystem.domain.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
