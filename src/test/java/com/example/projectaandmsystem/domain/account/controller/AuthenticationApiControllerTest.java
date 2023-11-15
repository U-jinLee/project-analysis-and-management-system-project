package com.example.projectaandmsystem.domain.account.controller;

import com.example.projectaandmsystem.IntegrationTest;
import com.example.projectaandmsystem.domain.account.dto.SignInDto;
import com.example.projectaandmsystem.domain.account.dto.SignUpDto;
import com.example.projectaandmsystem.domain.account.entity.Account;
import com.example.projectaandmsystem.domain.account.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthenticationApiControllerTest extends IntegrationTest {

    @Autowired
    AccountRepository accountRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void 회원_가입_성공() throws Exception {
        //given
        SignUpDto.Request request = SignUpDto.Request.from("yoojinlee.dev@gmail.com", "1q2w3e4r!", "이유진");

        mvc.perform(post("/api/authenticate/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void 로그인_성공() throws Exception {

        //given
        String rawPassword = "1q2w3e4r!";
        Account account = accountRepo.save(
                Account.builder()
                        .email("yoojinlee.dev@gmail.com")
                        .password(passwordEncoder.encode(rawPassword))
                        .name("이유진")
                        .build());

        SignInDto.Request request = SignInDto.Request.from(account.getEmail(), rawPassword);

        mvc.perform(get("/api/authenticate/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}