package com.nisum.user_management.domain.port;

import com.nisum.user_management.domain.model.UserResponse;

@FunctionalInterface
public interface FindUserRepository {
    UserResponse execute(String email);
}
