package com.example.projectaandmsystem.domain.account.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "account")
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 300)
    private String password;

    @Column(name = "name")
    private String name;

    @Builder
    public Account(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

}
