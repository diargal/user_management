package com.nisum.user_management.domain.service;

import com.nisum.user_management.domain.model.LoginRequest;
import com.nisum.user_management.domain.model.LoginResponse;
import com.nisum.user_management.domain.model.UserRequest;
import com.nisum.user_management.domain.model.UserResponse;
import com.nisum.user_management.domain.port.AuthRepository;
import com.nisum.user_management.domain.port.CreateUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CreateUserServiceTest {

    @Mock
    private CreateUserRepository createUserRepository;

    @Mock
    private AuthRepository authRepository;

    @InjectMocks
    private CreateUserService createUserService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void executeShouldSetDefaultValues() {
        // Arrange
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("password");

        UserResponse userResponse = new UserResponse();
        userResponse.setEmail("test@example.com");
        userResponse.setCreated(LocalDateTime.now());
        userResponse.setLastLogin(LocalDateTime.now());
        userResponse.setModified(LocalDateTime.now());
        userResponse.setActive(true);

        LoginResponse loginResponse = new LoginResponse("testToken");

        when(createUserRepository.execute(any(UserRequest.class))).thenReturn(userResponse);
        when(authRepository.execute(any(LoginRequest.class))).thenReturn(loginResponse);

        // Act
        UserResponse result = createUserService.execute(userRequest);

        // Assert
        assertNotNull(result.getCreated());
        assertNotNull(result.getLastLogin());
        assertNotNull(result.getModified());
        assertTrue(result.isActive());
    }

    @Test
    void executeShouldThrowExceptionIfRepositoryReturnsNull() {
        // Arrange
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("password");

        when(createUserRepository.execute(any(UserRequest.class))).thenReturn(null);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> createUserService.execute(userRequest));
    }

}