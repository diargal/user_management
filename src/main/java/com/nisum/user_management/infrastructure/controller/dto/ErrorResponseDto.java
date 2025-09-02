package com.nisum.user_management.infrastructure.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nisum.user_management.infrastructure.util.UserRequestUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponseDto {
    @JsonProperty(UserRequestUtil.MESSAGE)
    private String message;
}
