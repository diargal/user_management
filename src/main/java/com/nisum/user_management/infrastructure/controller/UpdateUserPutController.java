package com.nisum.user_management.infrastructure.controller;

import com.nisum.user_management.application.handler.UpdateUserHandler;
import com.nisum.user_management.infrastructure.controller.dto.UserRequestDto;
import com.nisum.user_management.infrastructure.controller.dto.CompleteUserResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UpdateUserPutController {

    private final UpdateUserHandler updateUserHandler;

    @PutMapping("/{id}")
    public ResponseEntity<CompleteUserResponseDto> updateUser(@PathVariable UUID id,
                                                              @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(updateUserHandler.execute(id, userRequestDto));
    }
}
