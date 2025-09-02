package com.nisum.user_management.application.handler;

import com.nisum.user_management.domain.service.FindUserService;
import com.nisum.user_management.infrastructure.controller.dto.UserResponseDto;
import com.nisum.user_management.infrastructure.controller.mapper.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class FindUserHandler {
    private final FindUserService service;
    private final UserMapper mapper;

    public FindUserHandler(FindUserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    public UserResponseDto execute(String email) {
        return mapper.responseToDto(service.execute(email));
    }
}
