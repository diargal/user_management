package com.nisum.user_management.domain.port;

import java.util.UUID;

@FunctionalInterface
public interface DeleteUserRepository {
    void execute(UUID id);
}
