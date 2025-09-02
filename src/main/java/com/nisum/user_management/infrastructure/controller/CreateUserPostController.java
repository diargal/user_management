package com.nisum.user_management.infrastructure.controller;

import com.nisum.user_management.application.handler.CreateUserHandler;
import com.nisum.user_management.infrastructure.controller.dto.CreateUserResponseDto;
import com.nisum.user_management.infrastructure.controller.dto.UserRequestDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class CreateUserPostController {

    private final CreateUserHandler createUserHandler;

    @PostMapping
    public ResponseEntity<CreateUserResponseDto> createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        CreateUserResponseDto response = createUserHandler.execute(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}