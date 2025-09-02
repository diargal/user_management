package com.nisum.user_management.infrastructure.adapter.persistence;

import com.nisum.user_management.domain.model.UserRequest;
import com.nisum.user_management.domain.model.UserResponse;
import com.nisum.user_management.domain.model.enums.ErrorMessageEnum;
import com.nisum.user_management.domain.port.UpdateUserRepository;
import com.nisum.user_management.domain.exception.UserNotFoundException;
import com.nisum.user_management.infrastructure.adapter.persistence.mapper.UserEntityMapper;
import com.nisum.user_management.infrastructure.adapter.persistence.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class UpdateUserAdapter implements UpdateUserRepository {

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    public UpdateUserAdapter(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public UserResponse execute(UUID id, UserRequest userRequest) {
        return userRepository.findById(id)
                .map(userEntity -> {
                    userEntityMapper.updateUserFromModel(userRequest, userEntity);
                    return userEntityMapper.responseToModel(userRepository.save(userEntity));
                })
                .orElseThrow(() -> new UserNotFoundException(ErrorMessageEnum.USER_NOT_FOUND_EXCEPTION.getMessage()));
    }
}
