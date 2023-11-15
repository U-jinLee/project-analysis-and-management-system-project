package com.example.projectaandmsystem.domain.account.entity;

import com.example.projectaandmsystem.domain.account.model.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "account")
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 300)
    private String password;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Account(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = Role.USER;
    }

}
