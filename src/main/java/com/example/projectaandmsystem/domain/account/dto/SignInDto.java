package com.example.projectaandmsystem.domain.account.dto;

import com.example.projectaandmsystem.domain.account.entity.Account;
import com.example.projectaandmsystem.domain.account.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignInDto {

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Request {

        @Email
        @NotEmpty
        private String email;

        @NotEmpty
        private String password;

        public static Request from(String email, String password) {
            return new Request(
                    email,
                    password);
        }

        public Account toEntity(PasswordEncoder encoder) {
            return Account.builder()
                    .email(email)
                    .password(encoder.encode(password))
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {
        private long id;
        private String email;
        private Role role;

        public static Response from(Account client) {
            return new Response(
                    client.getId(),
                    client.getEmail(),
                    client.getRole());
        }

    }

}
