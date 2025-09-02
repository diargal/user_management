package com.nisum.user_management.infrastructure.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nisum.user_management.infrastructure.util.UserRequestUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserResponseDto {
    private UUID id;
    private LocalDateTime created;
    private LocalDateTime modified;
    @JsonProperty(UserRequestUtil.LAST_LOGIN)
    private LocalDateTime lastLogin;
    private String token;
    @JsonProperty(UserRequestUtil.IS_ACTIVE)
    private boolean isActive;
}
