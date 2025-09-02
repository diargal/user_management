package com.nisum.user_management.application.handler;

import com.nisum.user_management.domain.service.DeleteUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.Mockito.verify;

class DeleteUserHandlerTest {

    @Mock
    private DeleteUserService service;

    @InjectMocks
    private DeleteUserHandler handler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void executeShouldCallServiceWithCorrectId() {
        // Arrange
        UUID userId = UUID.randomUUID();

        // Act
        handler.execute(userId);

        // Assert
        verify(service).execute(userId);
    }
}