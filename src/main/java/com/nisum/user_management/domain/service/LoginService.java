package com.nisum.user_management.domain.service;

import com.nisum.user_management.domain.model.LoginRequest;
import com.nisum.user_management.domain.model.LoginResponse;
import com.nisum.user_management.domain.port.AuthRepository;

public class LoginService {
    private final AuthRepository repository;

    public LoginService(AuthRepository repository) {
        this.repository = repository;
    }

    public LoginResponse execute(LoginRequest loginRequest) {
        return repository.execute(loginRequest);
    }
}
