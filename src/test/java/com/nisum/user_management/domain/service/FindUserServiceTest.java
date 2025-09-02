package com.nisum.user_management.domain.service;

import com.nisum.user_management.domain.model.UserResponse;
import com.nisum.user_management.domain.port.FindUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FindUserServiceTest {

    @Mock
    private FindUserRepository repository;

    @InjectMocks
    private FindUserService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void executeShouldReturnUserResponse() {
        // Arrange
        String email = "test@example.com";
        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(email);

        when(repository.execute(anyString())).thenReturn(userResponse);

        // Act
        UserResponse result = service.execute(email);

        // Assert
        verify(repository).execute(email);
        assertEquals(userResponse, result);
    }
}