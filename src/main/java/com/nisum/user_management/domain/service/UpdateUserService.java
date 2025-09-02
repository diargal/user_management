package com.nisum.user_management.domain.service;

import com.nisum.user_management.domain.model.UserRequest;
import com.nisum.user_management.domain.model.UserResponse;
import com.nisum.user_management.domain.port.UpdateUserRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public class UpdateUserService {
    private final UpdateUserRepository repository;

    public UpdateUserService(UpdateUserRepository repository) {
        this.repository = repository;
    }

    public UserResponse execute(UUID id, UserRequest userRequest) {
        userRequest.setModified(LocalDateTime.now());
        return repository.execute(id, userRequest);
    }
}
