package com.nisum.user_management.application.handler;

import com.nisum.user_management.domain.model.UserRequest;
import com.nisum.user_management.domain.service.UpdateUserService;
import com.nisum.user_management.infrastructure.controller.dto.CreateUserResponseDto;
import com.nisum.user_management.infrastructure.controller.dto.UserRequestDto;
import com.nisum.user_management.infrastructure.controller.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UpdateUserHandler {
    private final UpdateUserService service;
    private final UserMapper mapper;

    public UpdateUserHandler(UpdateUserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    public CreateUserResponseDto execute(UUID id, UserRequestDto userRequestDto) {
        UserRequest userRequest = mapper.requestToModel(userRequestDto);
        return mapper.responseToCompleteDto(service.execute(id, userRequest));
    }
}
