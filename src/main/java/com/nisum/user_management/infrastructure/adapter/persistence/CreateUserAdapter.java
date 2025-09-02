package com.nisum.user_management.infrastructure.adapter.persistence;

import com.nisum.user_management.domain.model.UserRequest;
import com.nisum.user_management.domain.model.UserResponse;
import com.nisum.user_management.domain.model.enums.ErrorMessageEnum;
import com.nisum.user_management.domain.port.CreateUserRepository;
import com.nisum.user_management.domain.exception.ExistEmailException;
import com.nisum.user_management.infrastructure.adapter.persistence.entity.RoleEntity;
import com.nisum.user_management.infrastructure.adapter.persistence.entity.UserEntity;
import com.nisum.user_management.infrastructure.adapter.persistence.mapper.RoleMapper;
import com.nisum.user_management.infrastructure.adapter.persistence.mapper.UserEntityMapper;
import com.nisum.user_management.infrastructure.adapter.persistence.repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class CreateUserAdapter implements CreateUserRepository {
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final RoleMapper mapper;

    public CreateUserAdapter(UserRepository userRepository, UserEntityMapper userEntityMapper, RoleMapper mapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public UserResponse execute(UserRequest userRequest) {
        UserEntity userEntity = userEntityMapper.toEntity(userRequest);
        Set<RoleEntity> roles = userRequest.getRoles().stream()
                .map(mapper::roleEnumToRoleEntity)
                .collect(Collectors.toSet());
        userEntity.setRoles(roles);
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new ExistEmailException(ErrorMessageEnum.EMAIL_EXIST_EXCEPTION.getMessage());
        }

        return userEntityMapper.responseToModel(userRepository.save(userEntity));
    }
}