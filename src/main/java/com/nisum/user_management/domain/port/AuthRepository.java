package com.nisum.user_management.domain.port;

import com.nisum.user_management.domain.model.LoginRequest;
import com.nisum.user_management.domain.model.LoginResponse;

@FunctionalInterface
public interface AuthRepository {
    LoginResponse execute(LoginRequest loginRequest);
}
