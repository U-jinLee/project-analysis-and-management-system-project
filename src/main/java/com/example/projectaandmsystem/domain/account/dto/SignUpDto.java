package com.example.projectaandmsystem.domain.account.dto;

import com.example.projectaandmsystem.domain.account.entity.Account;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpDto {

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Request {

        @Email
        @NotEmpty
        private String email;

        @NotEmpty
        private String password;

        @NotEmpty
        private String name;

        public static Request from(String email, String password, String name) {
            return new Request(
                    email,
                    password,
                    name);
        }

        public Account toEntity(PasswordEncoder encoder) {
            return Account.builder()
                    .email(this.email)
                    .password(encoder.encode(this.password))
                    .name(name)
                    .build();
        }

    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {

        private long id;

        private String email;

        private String name;

        public static Response from(Account account) {
            return new Response(
                    account.getId(),
                    account.getEmail(),
                    account.getName());
        }

    }

}
