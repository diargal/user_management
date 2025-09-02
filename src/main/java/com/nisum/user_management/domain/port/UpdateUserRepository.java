package com.nisum.user_management.domain.port;

import com.nisum.user_management.domain.model.UserRequest;
import com.nisum.user_management.domain.model.UserResponse;

import java.util.UUID;

@FunctionalInterface
public interface UpdateUserRepository {
    UserResponse execute(UUID id, UserRequest userRequest);
}