package com.example.projectaandmsystem.domain.account.controller;

import com.example.projectaandmsystem.domain.account.dto.SignUpDto;
import com.example.projectaandmsystem.domain.account.service.AccountSignUpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/authenticate")
@RestController
public class AuthenticationApiController {

    private final AccountSignUpService signUpService;

    @PostMapping("/sign-up")
    public ResponseEntity<SignUpDto.Response> signUp(@RequestBody @Valid SignUpDto.Request request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(signUpService.signUp(request));
    }
}
