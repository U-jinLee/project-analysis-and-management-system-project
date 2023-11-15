package com.example.projectaandmsystem.domain.account.service;

import com.example.projectaandmsystem.domain.account.dto.SignInDto;
import com.example.projectaandmsystem.domain.account.dto.TokenDto;
import com.example.projectaandmsystem.global.jwt.JwtProvider;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AccountSignInService {

    private final JwtProvider jwtProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Transactional
    public TokenDto signIn(SignInDto.Request request) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject()
                .authenticate(authenticationToken);

        TokenDto tokenDto = jwtProvider.generateTokens(authentication);

        return tokenDto;
    }


    private void setHeader(HttpServletResponse servletResponse, TokenDto tokenDto) {
        servletResponse.addHeader("Autorization", tokenDto.getGrantType()+" "+tokenDto.getAccessToken());
        servletResponse.addHeader("Refresh_Token", tokenDto.getRefreshToken());
    }

}