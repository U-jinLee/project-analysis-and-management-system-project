package com.example.projectaandmsystem.domain.account.service;

import com.example.projectaandmsystem.domain.account.entity.Account;
import com.example.projectaandmsystem.domain.account.exception.ClientNotFoundException;
import com.example.projectaandmsystem.domain.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final AccountRepository accountRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepo.findByEmail(username)
                .map(this::createUserDetails)
                .orElseThrow(ClientNotFoundException::new);
    }

    private UserDetails createUserDetails(Account account) {
        return User.builder()
                .username(account.getEmail())
                .password(account.getPassword())
                .roles(account.getRole().toString())
                .build();
    }
}
