package com.nisum.user_management.application.handler;

import com.nisum.user_management.domain.model.UserResponse;
import com.nisum.user_management.domain.service.FindUserService;
import com.nisum.user_management.infrastructure.controller.dto.UserResponseDto;
import com.nisum.user_management.infrastructure.controller.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FindUserHandlerTest {

    @Mock
    private FindUserService service;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    private FindUserHandler handler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void executeShouldReturnUserResponseDto() {
        // Arrange
        String email = "test@example.com";
        UserResponse userResponse = new UserResponse();
        UserResponseDto userResponseDto = new UserResponseDto();

        when(service.execute(anyString())).thenReturn(userResponse);
        when(mapper.responseToDto(any(UserResponse.class))).thenReturn(userResponseDto);

        // Act
        UserResponseDto result = handler.execute(email);

        // Assert
        verify(service).execute(email);
        verify(mapper).responseToDto(userResponse);
        assertEquals(userResponseDto, result);
    }
}