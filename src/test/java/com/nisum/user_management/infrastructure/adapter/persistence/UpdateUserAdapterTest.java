package com.nisum.user_management.infrastructure.adapter.persistence;

import com.nisum.user_management.domain.exception.UserNotFoundException;
import com.nisum.user_management.domain.model.UserRequest;
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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UpdateUserAdapterTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserEntityMapper userEntityMapper;

    @InjectMocks
    private UpdateUserAdapter updateUserAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void executeShouldUpdateUserAndReturnUserResponse() {
        // Arrange
        UUID userId = UUID.randomUUID();
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("updated@example.com");

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        userEntity.setEmail("old@example.com");

        UserEntity updatedUserEntity = new UserEntity();
        updatedUserEntity.setId(userId);
        updatedUserEntity.setEmail("updated@example.com");

        UserResponse userResponse = new UserResponse();
        userResponse.setEmail("updated@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        when(userRepository.save(userEntity)).thenReturn(updatedUserEntity);
        when(userEntityMapper.responseToModel(updatedUserEntity)).thenReturn(userResponse);

        // Act
        UserResponse result = updateUserAdapter.execute(userId, userRequest);

        // Assert
        verify(userRepository).findById(userId);
        verify(userEntityMapper).updateUserFromModel(userRequest, userEntity);
        verify(userRepository).save(userEntity);
        verify(userEntityMapper).responseToModel(updatedUserEntity);
        assertEquals(userResponse, result);
    }

    @Test
    void executeShouldThrowUserNotFoundExceptionWhenUserDoesNotExist() {
        // Arrange
        UUID userId = UUID.randomUUID();
        UserRequest userRequest = new UserRequest();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> updateUserAdapter.execute(userId, userRequest));
        verify(userRepository).findById(userId);
        verify(userEntityMapper, never()).updateUserFromModel(any(UserRequest.class), any(UserEntity.class));
        verify(userRepository, never()).save(any(UserEntity.class));
    }
}