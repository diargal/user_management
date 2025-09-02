package com.nisum.user_management.infrastructure.controller.mapper;

import com.nisum.user_management.domain.model.LoginRequest;
import com.nisum.user_management.domain.model.LoginResponse;
import com.nisum.user_management.infrastructure.controller.dto.LoginRequestDto;
import com.nisum.user_management.infrastructure.controller.dto.LoginResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface LoginMapper {
    LoginRequest requestToModel(LoginRequestDto loginRequestDto);

    LoginResponseDto responseToDto(LoginResponse response);

}
