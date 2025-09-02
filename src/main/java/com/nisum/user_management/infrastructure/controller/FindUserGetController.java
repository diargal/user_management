package com.nisum.user_management.infrastructure.controller;

import com.nisum.user_management.application.handler.FindUserHandler;
import com.nisum.user_management.infrastructure.controller.dto.UserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class FindUserGetController {

    private final FindUserHandler findUserHandler;

    public FindUserGetController(FindUserHandler findUserHandler) {
        this.findUserHandler = findUserHandler;
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserResponseDto> findUser(@PathVariable String email) {
        return ResponseEntity.ok(findUserHandler.execute(email));
    }
}
