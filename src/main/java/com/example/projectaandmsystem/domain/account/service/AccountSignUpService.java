package com.example.projectaandmsystem.domain.account.service;

import com.example.projectaandmsystem.domain.account.dto.SignUpDto;
import com.example.projectaandmsystem.domain.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountSignUpService {

    private final AccountRepository accountRepo;
    private final PasswordEncoder passwordEncoder;

    public SignUpDto.Response signUp(SignUpDto.Request request) {
        return SignUpDto.Response.from(accountRepo.save(request.toEntity(passwordEncoder)));
    }

}