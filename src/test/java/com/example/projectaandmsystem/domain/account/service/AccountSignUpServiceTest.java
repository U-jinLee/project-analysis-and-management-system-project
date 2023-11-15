package com.example.projectaandmsystem.domain.account.service;

import com.example.projectaandmsystem.IntegrationTest;
import com.example.projectaandmsystem.domain.account.dto.SignUpDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AccountSignUpServiceTest extends IntegrationTest {

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
}