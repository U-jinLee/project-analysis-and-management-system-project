package com.example.projectaandmsystem.domain.account.controller;

import com.example.projectaandmsystem.domain.account.dto.SignInDto;
import com.example.projectaandmsystem.domain.account.dto.SignUpDto;
import com.example.projectaandmsystem.domain.account.dto.TokenDto;
import com.example.projectaandmsystem.domain.account.service.AccountSignInService;
import com.example.projectaandmsystem.domain.account.service.AccountSignUpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/authenticate")
@RestController
public class AuthenticationApiController {

    private final AccountSignUpService signUpService;
    private final AccountSignInService signInService;

    @PostMapping("/sign-up")
    public ResponseEntity<SignUpDto.Response> signUp(@RequestBody @Valid SignUpDto.Request request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(signUpService.signUp(request));
    }

    @GetMapping("/sign-in")
    public ResponseEntity<TokenDto> signIn(@RequestBody @Valid SignInDto.Request request) {
        return ResponseEntity.status(HttpStatus.OK).body(signInService.signIn(request));
    }

}
