package com.nisum.user_management.infrastructure.controller;

import com.nisum.user_management.application.handler.DeleteUserHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class DeleteUserDeleteController {

    private final DeleteUserHandler deleteUserHandler;

    public DeleteUserDeleteController(DeleteUserHandler deleteUserHandler) {
        this.deleteUserHandler = deleteUserHandler;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        deleteUserHandler.execute(id);
    }
}
