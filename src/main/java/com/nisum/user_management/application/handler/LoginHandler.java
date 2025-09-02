package com.nisum.user_management.application.handler;

import com.nisum.user_management.domain.service.LoginService;
import com.nisum.user_management.infrastructure.controller.dto.LoginRequestDto;
import com.nisum.user_management.infrastructure.controller.dto.LoginResponseDto;
import com.nisum.user_management.infrastructure.controller.mapper.LoginMapper;
import org.springframework.stereotype.Component;

@Component
public class LoginHandler {
    private final LoginMapper loginMapper;
    private final LoginService loginService;

    public LoginHandler(LoginMapper loginMapper, LoginService loginService) {
        this.loginMapper = loginMapper;
        this.loginService = loginService;
    }

    public LoginResponseDto execute(LoginRequestDto loginRequestDto) {
        return loginMapper.responseToDto(loginService.execute(loginMapper.requestToModel(loginRequestDto)));
    }
}
