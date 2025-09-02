package com.nisum.user_management.infrastructure.adapter.persistence;

import com.nisum.user_management.domain.exception.UserNotFoundException;
import com.nisum.user_management.domain.model.UserResponse;
import com.nisum.user_management.infrastructure.adapter.persistence.entity.UserEntity;
import com.nisum.user_management.infrastructure.adapter.persistence.mapper.UserEntityMapper;
import com.nisum.user_management.infrastructure.adapter.persistence.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FindUserAdapterTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserEntityMapper userEntityMapper;

    @InjectMocks
    private FindUserAdapter findUserAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void executeShouldReturnUserResponseWhenUserExists() {
        // Arrange
        String email = "test@example.com";
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);

        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(userEntity));
        when(userEntityMapper.responseToModel(userEntity)).thenReturn(userResponse);

        // Act
        UserResponse result = findUserAdapter.execute(email);

        // Assert
        verify(userRepository).findByEmail(email);
        verify(userEntityMapper).responseToModel(userEntity);
        assertEquals(userResponse, result);
    }

    @Test
    void executeShouldThrowUserNotFoundExceptionWhenUserDoesNotExist() {
        // Arrange
        String email = "nonexistent@example.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> findUserAdapter.execute(email));
        verify(userRepository).findByEmail(email);
        verify(userEntityMapper, never()).responseToModel(any(UserEntity.class));
    }
}