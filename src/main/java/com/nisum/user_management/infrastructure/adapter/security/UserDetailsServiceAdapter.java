package com.nisum.user_management.infrastructure.adapter.security;

import com.nisum.user_management.domain.model.enums.ErrorMessageEnum;
import com.nisum.user_management.domain.exception.UserNotFoundException;
import com.nisum.user_management.infrastructure.adapter.persistence.entity.UserEntity;
import com.nisum.user_management.infrastructure.adapter.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceAdapter implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format(ErrorMessageEnum.LOGIN_ERROR_BY_USER_NOT_FOUND.getMessage(), email)
                ));

        Collection<? extends GrantedAuthority> authorities = userEntity.getRoles()
                .stream()
                .map(role ->
                        new SimpleGrantedAuthority("ROLE_".concat(role.getName().name())))
                .collect(Collectors.toSet());

        return new User(userEntity.getEmail(),
                userEntity.getPassword(),
                true,
                true,
                true,
                true,
                authorities);
    }
}