package com.nisum.user_management.infrastructure.adapter.persistence;

import com.nisum.user_management.domain.exception.UserNotFoundException;
import com.nisum.user_management.infrastructure.adapter.persistence.entity.UserEntity;
import com.nisum.user_management.infrastructure.adapter.persistence.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DeleteUserAdapterTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DeleteUserAdapter deleteUserAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void executeShouldDeleteUserWhenUserExists() {
        // Arrange
        UUID userId = UUID.randomUUID();
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));

        // Act
        deleteUserAdapter.execute(userId);

        // Assert
        verify(userRepository).delete(userEntity);
    }

    @Test
    void executeShouldThrowUserNotFoundExceptionWhenUserDoesNotExist() {
        // Arrange
        UUID userId = UUID.randomUUID();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> deleteUserAdapter.execute(userId));
        verify(userRepository, never()).delete(any(UserEntity.class));
    }
}