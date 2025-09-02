package com.nisum.user_management.application.handler;

import com.nisum.user_management.domain.model.UserRequest;
import com.nisum.user_management.domain.service.CreateUserService;
import com.nisum.user_management.infrastructure.controller.dto.CreateUserResponseDto;
import com.nisum.user_management.infrastructure.controller.dto.UserRequestDto;
import com.nisum.user_management.infrastructure.controller.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CreateUserHandler {
    private final CreateUserService service;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public CreateUserHandler(CreateUserService service, UserMapper mapper, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    public CreateUserResponseDto execute(UserRequestDto userRequestDto) {
        UserRequest userRequest = mapper.requestToModel(userRequestDto);
        userRequest.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        return mapper.responseToCompleteDto(service.execute(userRequest));
    }
}
