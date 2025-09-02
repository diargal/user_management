package com.nisum.user_management.application.handler;

import com.nisum.user_management.domain.service.DeleteUserService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeleteUserHandler {
    private final DeleteUserService service;

    public DeleteUserHandler(DeleteUserService service) {
        this.service = service;
    }

    public void execute(UUID id) {
        service.execute(id);
    }
}
