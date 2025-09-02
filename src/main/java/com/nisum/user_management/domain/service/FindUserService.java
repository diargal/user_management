package com.nisum.user_management.domain.service;

import com.nisum.user_management.domain.model.UserResponse;
import com.nisum.user_management.domain.port.FindUserRepository;

public class FindUserService {
    private final FindUserRepository repository;

    public FindUserService(FindUserRepository repository) {
        this.repository = repository;
    }

    public UserResponse execute(String email) {
        return repository.execute(email);
    }
}