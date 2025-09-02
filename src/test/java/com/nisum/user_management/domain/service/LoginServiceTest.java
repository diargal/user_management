package com.nisum.user_management.domain.service;

import com.nisum.user_management.domain.model.LoginRequest;
import com.nisum.user_management.domain.model.LoginResponse;
import com.nisum.user_management.domain.port.AuthRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class LoginServiceTest {

    @Mock
    private AuthRepository repository;

    @InjectMocks
    private LoginService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void executeShouldReturnLoginResponse() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password");

        LoginResponse loginResponse = new LoginResponse("testToken");

        when(repository.execute(any(LoginRequest.class))).thenReturn(loginResponse);

        // Act
        LoginResponse result = service.execute(loginRequest);

        // Assert
        verify(repository).execute(loginRequest);
        assertEquals(loginResponse, result);
    }
}