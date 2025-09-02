package com.nisum.user_management.infrastructure.adapter.persistence;

import com.nisum.user_management.domain.exception.ExistEmailException;
import com.nisum.user_management.domain.model.UserRequest;
import com.nisum.user_management.domain.model.UserResponse;
import com.nisum.user_management.infrastructure.adapter.persistence.entity.RoleEntity;
import com.nisum.user_management.infrastructure.adapter.persistence.entity.UserEntity;
import com.nisum.user_management.infrastructure.adapter.persistence.mapper.RoleMapper;
import com.nisum.user_management.infrastructure.adapter.persistence.mapper.UserEntityMapper;
import com.nisum.user_management.infrastructure.adapter.persistence.repository.UserRepository;
import com.nisum.user_management.infrastructure.adapter.security.model.enums.RoleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateUserAdapterTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserEntityMapper userEntityMapper;

    @Mock
    private RoleMapper roleMapper;

    @InjectMocks
    private CreateUserAdapter createUserAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void executeShouldSaveUserAndReturnUserResponse() {
        // Arrange
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("test@example.com");
        userRequest.setRoles(Set.of(RoleEnum.USER));

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("test@example.com");

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(RoleEnum.USER);

        UserResponse userResponse = new UserResponse();
        userResponse.setEmail("test@example.com");

        when(userEntityMapper.toEntity(any(UserRequest.class))).thenReturn(userEntity);
        when(roleMapper.roleEnumToRoleEntity(RoleEnum.USER)).thenReturn(roleEntity);
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        when(userEntityMapper.responseToModel(any(UserEntity.class))).thenReturn(userResponse);

        // Act
        UserResponse result = createUserAdapter.execute(userRequest);

        // Assert
        verify(userRepository).save(userEntity);
        assertEquals(userResponse, result);
    }

    @Test
    void executeShouldThrowExistEmailExceptionWhenEmailExists() {
        // Arrange
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("test@example.com");
        userRequest.setRoles(Set.of(RoleEnum.USER));

        UserEntity existingUser = new UserEntity();
        existingUser.setEmail("test@example.com");

        UserResponse userResponse = new UserResponse();
        userResponse.setEmail("test@example.com");

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(existingUser));
        when(userEntityMapper.toEntity(any(UserRequest.class))).thenReturn(existingUser);

        // Act & Assert
        assertThrows(ExistEmailException.class, () -> createUserAdapter.execute(userRequest));
        verify(userRepository, never()).save(any(UserEntity.class));
    }


}