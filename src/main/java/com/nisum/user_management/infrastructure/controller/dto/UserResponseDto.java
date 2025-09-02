package com.nisum.user_management.infrastructure.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nisum.user_management.infrastructure.util.UserRequestUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private UUID id;
    private String name;
    private String email;
    private List<PhoneDto> phones;
    private LocalDateTime created;
    private LocalDateTime modified;
    @JsonProperty(UserRequestUtil.LAST_LOGIN)
    private LocalDateTime lastLogin;
    @JsonProperty(UserRequestUtil.IS_ACTIVE)
    private boolean isActive;
}
