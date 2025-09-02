package com.nisum.user_management.domain.service;

import com.nisum.user_management.domain.model.UserRequest;
import com.nisum.user_management.domain.model.UserResponse;
import com.nisum.user_management.domain.port.UpdateUserRepository;
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

class UpdateUserServiceTest {

    @Mock
    private UpdateUserRepository repository;

    @InjectMocks
    private UpdateUserService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void executeShouldReturnUserResponse() {
        // Arrange
        UUID userId = UUID.randomUUID();
        UserRequest userRequest = new UserRequest();
        UserResponse userResponse = new UserResponse();

        when(repository.execute(any(UUID.class), any(UserRequest.class))).thenReturn(userResponse);

        // Act
        UserResponse result = service.execute(userId, userRequest);

        // Assert
        verify(repository).execute(userId, userRequest);
        assertEquals(userResponse, result);
    }
}