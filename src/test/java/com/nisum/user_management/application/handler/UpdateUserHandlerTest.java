package com.nisum.user_management.application.handler;

import com.nisum.user_management.domain.model.UserRequest;
import com.nisum.user_management.domain.model.UserResponse;
import com.nisum.user_management.domain.service.UpdateUserService;
import com.nisum.user_management.infrastructure.controller.dto.CreateUserResponseDto;
import com.nisum.user_management.infrastructure.controller.dto.UserRequestDto;
import com.nisum.user_management.infrastructure.controller.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UpdateUserHandlerTest {

    @Mock
    private UpdateUserService service;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    private UpdateUserHandler handler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void executeShouldReturnUserResponseDto() {
        // Arrange
        UUID userId = UUID.randomUUID();
        UserRequestDto userRequestDto = new UserRequestDto();
        UserRequest userRequest = new UserRequest();
        UserResponse userResponse = new UserResponse();
        CreateUserResponseDto createUserResponseDto = new CreateUserResponseDto();

        when(mapper.requestToModel(any(UserRequestDto.class))).thenReturn(userRequest);
        when(service.execute(any(UUID.class), any(UserRequest.class))).thenReturn(userResponse);
        when(mapper.responseToCompleteDto(any(UserResponse.class))).thenReturn(createUserResponseDto);

        // Act
        CreateUserResponseDto result = handler.execute(userId, userRequestDto);

        // Assert
        verify(mapper).requestToModel(userRequestDto);
        verify(service).execute(userId, userRequest);
        verify(mapper).responseToCompleteDto(userResponse);
        assertEquals(createUserResponseDto, result);
    }
}