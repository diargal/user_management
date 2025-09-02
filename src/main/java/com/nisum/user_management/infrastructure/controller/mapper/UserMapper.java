package com.nisum.user_management.infrastructure.controller.mapper;

import com.nisum.user_management.domain.model.UserRequest;
import com.nisum.user_management.domain.model.UserResponse;
import com.nisum.user_management.infrastructure.controller.dto.UserRequestDto;
import com.nisum.user_management.infrastructure.controller.dto.CompleteUserResponseDto;
import com.nisum.user_management.infrastructure.controller.dto.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {
    CompleteUserResponseDto responseToCompleteDto(UserResponse userResponse);
    UserResponseDto responseToDto(UserResponse userResponse);

    UserRequest requestToModel(UserRequestDto userRequestDto);
}
