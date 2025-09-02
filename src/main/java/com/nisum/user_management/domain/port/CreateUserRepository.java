package com.nisum.user_management.domain.port;

import com.nisum.user_management.domain.model.UserRequest;
import com.nisum.user_management.domain.model.UserResponse;

@FunctionalInterface
public interface CreateUserRepository {
    UserResponse execute(UserRequest userRequest);
}
