package com.pm.authservice.service;

import com.pm.authservice.dto.loginRequestDto;
import com.pm.authservice.model.User;

import com.pm.authservice.util.jwtutil;
import io.jsonwebtoken.JwtException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class authService {

    private final userService userService;
    private  final PasswordEncoder passwordEncoder;
    private  final jwtutil jwtUtil;


    public authService(userService userService, PasswordEncoder passwordEncoder, jwtutil jwtUtil) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }


    public Optional<String> authenticate(loginRequestDto loginRequestdto) {
        Optional<String> token = userService.findByEmail(loginRequestdto.getEmail())
                .filter(u -> passwordEncoder.matches(loginRequestdto.getPassword(), u.getPassword()))
                .map(u -> jwtUtil.generateToken(u.getEmail(), u.getRole()));

        return token;
    }

    public boolean validateToken(String token) {
        try {
            jwtUtil.validateToken(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
