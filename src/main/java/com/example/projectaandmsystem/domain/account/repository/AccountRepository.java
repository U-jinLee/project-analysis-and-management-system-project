package com.example.projectaandmsystem.domain.account.repository;

import com.example.projectaandmsystem.domain.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}