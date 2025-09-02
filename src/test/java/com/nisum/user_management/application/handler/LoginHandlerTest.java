package com.nisum.user_management.application.handler;

import com.nisum.user_management.domain.model.LoginRequest;
import com.nisum.user_management.domain.model.LoginResponse;
import com.nisum.user_management.domain.service.LoginService;
import com.nisum.user_management.infrastructure.controller.dto.LoginRequestDto;
import com.nisum.user_management.infrastructure.controller.dto.LoginResponseDto;
import com.nisum.user_management.infrastructure.controller.mapper.LoginMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class LoginHandlerTest {

    @Mock
    private LoginMapper loginMapper;

    @Mock
    private LoginService loginService;

    @InjectMocks
    private LoginHandler loginHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void executeShouldReturnLoginResponseDto() {
        // Arrange
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        LoginRequest loginRequest = new LoginRequest();
        LoginResponse loginResponse = new LoginResponse();
        LoginResponseDto loginResponseDto = new LoginResponseDto();

        when(loginMapper.requestToModel(any(LoginRequestDto.class))).thenReturn(loginRequest);
        when(loginService.execute(any(LoginRequest.class))).thenReturn(loginResponse);
        when(loginMapper.responseToDto(any(LoginResponse.class))).thenReturn(loginResponseDto);

        // Act
        LoginResponseDto result = loginHandler.execute(loginRequestDto);

        // Assert
        verify(loginMapper).requestToModel(loginRequestDto);
        verify(loginService).execute(loginRequest);
        verify(loginMapper).responseToDto(loginResponse);
        assertEquals(loginResponseDto, result);
    }
}