package com.nisum.user_management.infrastructure.controller.dto;

import com.nisum.user_management.infrastructure.util.UserRequestUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    @NotBlank
    private String name;
    @Email(message = UserRequestUtil.MESSAGE_EMAIL, regexp = UserRequestUtil.REGULAR_EXPRESSION_EMAIL)
    @NotEmpty(message = UserRequestUtil.MESSAGE_EMAIL)
    private String email;
    @NotBlank(message = UserRequestUtil.PASSWORD_EMPTY)
    @Pattern(regexp = UserRequestUtil.REGULAR_EXPRESSION_PASSWORD, message = UserRequestUtil.MESSAGE_EXPRESSION_PASSWORD)
    private String password;
    @Valid
    private List<PhoneDto> phones;
    private boolean isactive;
}
