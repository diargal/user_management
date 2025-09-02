package com.nisum.user_management.application.handler;

import com.nisum.user_management.domain.model.UserRequest;
import com.nisum.user_management.domain.model.UserResponse;
import com.nisum.user_management.domain.service.CreateUserService;
import com.nisum.user_management.infrastructure.controller.dto.CompleteUserResponseDto;
import com.nisum.user_management.infrastructure.controller.dto.UserRequestDto;
import com.nisum.user_management.infrastructure.controller.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateUserHandlerTest {

    @Mock
    private CreateUserService service;

    @Mock
    private UserMapper mapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CreateUserHandler handler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void executeShouldReturnUserResponseDto() {
        // Arrange
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setPassword("plainPassword");

        UserRequest userRequest = new UserRequest();
        userRequest.setPassword("encodedPassword");
        UserResponse userResponse = new UserResponse();

        CompleteUserResponseDto completeUserResponseDto = new CompleteUserResponseDto();

        when(mapper.requestToModel(any(UserRequestDto.class))).thenReturn(userRequest);
        when(passwordEncoder.encode("plainPassword")).thenReturn("encodedPassword");
        when(service.execute(any(UserRequest.class))).thenReturn(userResponse);
        when(mapper.responseToCompleteDto(any(UserResponse.class))).thenReturn(completeUserResponseDto);

        // Act
        CompleteUserResponseDto result = handler.execute(userRequestDto);

        // Assert
        verify(passwordEncoder).encode("plainPassword");
        verify(mapper).requestToModel(userRequestDto);
        verify(service).execute(userRequest);
        verify(mapper).responseToCompleteDto(userResponse);
        assertEquals(completeUserResponseDto, result);
    }
}