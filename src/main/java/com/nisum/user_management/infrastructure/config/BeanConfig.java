package com.nisum.user_management.infrastructure.config;

import com.nisum.user_management.domain.port.AuthRepository;
import com.nisum.user_management.domain.port.CreateUserRepository;
import com.nisum.user_management.domain.port.DeleteUserRepository;
import com.nisum.user_management.domain.port.FindUserRepository;
import com.nisum.user_management.domain.port.UpdateUserRepository;
import com.nisum.user_management.domain.service.CreateUserService;
import com.nisum.user_management.domain.service.DeleteUserService;
import com.nisum.user_management.domain.service.FindUserService;
import com.nisum.user_management.domain.service.LoginService;
import com.nisum.user_management.domain.service.UpdateUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public CreateUserService createUserServiceBean(CreateUserRepository repository,
                                                   AuthRepository authRepository) {
        return new CreateUserService(repository, authRepository);
    }

    @Bean
    public UpdateUserService updateUserServiceBean(UpdateUserRepository userRepository) {
        return new UpdateUserService(userRepository);
    }

    @Bean
    public DeleteUserService deleteUserServiceBean(DeleteUserRepository userRepository) {
        return new DeleteUserService(userRepository);
    }

    @Bean
    public FindUserService findUserServiceBean(FindUserRepository userRepository) {
        return new FindUserService(userRepository);
    }

    @Bean
    public LoginService loginServiceBean(AuthRepository repository) {
        return new LoginService(repository);
    }
}
