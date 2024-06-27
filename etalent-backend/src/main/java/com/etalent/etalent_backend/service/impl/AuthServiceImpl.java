package com.etalent.etalent_backend.service.impl;

import com.etalent.etalent_backend.dto.AdminLoginDto;
import com.etalent.etalent_backend.security.JwtTokenProvider;
import com.etalent.etalent_backend.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;

    @Override
    public String login(AdminLoginDto adminLoginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                adminLoginDto.getCorreoAdmin(), //correo
                adminLoginDto.getContraAdmin()  //contraseña
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = JwtTokenProvider.generateToken(authentication);

        return token;
    }
}
