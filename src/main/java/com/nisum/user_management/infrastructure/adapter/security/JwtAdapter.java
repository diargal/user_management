package com.nisum.user_management.infrastructure.adapter.security;

import com.nisum.user_management.domain.model.LoginRequest;
import com.nisum.user_management.domain.model.LoginResponse;
import com.nisum.user_management.domain.port.AuthRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JwtAdapter implements AuthRepository {
    private final JwtUtils jwtUtils;

    public JwtAdapter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public LoginResponse execute(LoginRequest loginRequest) {
        return new LoginResponse(jwtUtils.generateAccesToken(loginRequest.getEmail()));
    }
}