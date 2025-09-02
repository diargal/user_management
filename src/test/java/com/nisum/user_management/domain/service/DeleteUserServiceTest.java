package com.nisum.user_management.domain.service;

import com.nisum.user_management.domain.port.DeleteUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.Mockito.verify;

class DeleteUserServiceTest {

    @Mock
    private DeleteUserRepository repository;

    @InjectMocks
    private DeleteUserService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void executeShouldCallRepositoryWithCorrectId() {
        // Arrange
        UUID userId = UUID.randomUUID();

        // Act
        service.execute(userId);

        // Assert
        verify(repository).execute(userId);
    }
}