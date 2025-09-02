package com.nisum.user_management.infrastructure.adapter.persistence;

import com.nisum.user_management.domain.model.UserResponse;
import com.nisum.user_management.domain.model.enums.ErrorMessageEnum;
import com.nisum.user_management.domain.port.FindUserRepository;
import com.nisum.user_management.domain.exception.UserNotFoundException;
import com.nisum.user_management.infrastructure.adapter.persistence.mapper.UserEntityMapper;
import com.nisum.user_management.infrastructure.adapter.persistence.repository.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class FindUserAdapter implements FindUserRepository {
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    public FindUserAdapter(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public UserResponse execute(String email) {
        return userRepository.findByEmail(email)
                .map(userEntityMapper::responseToModel)
                .orElseThrow(() -> new UserNotFoundException(ErrorMessageEnum.USER_NOT_FOUND_EXCEPTION.getMessage()));
    }
}
