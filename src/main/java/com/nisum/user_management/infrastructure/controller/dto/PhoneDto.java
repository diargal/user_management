package com.nisum.user_management.infrastructure.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nisum.user_management.infrastructure.util.UserRequestUtil;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class PhoneDto {
    @NotBlank(message = UserRequestUtil.NUMBER_EMPTY)
    private String number;
    @JsonProperty(UserRequestUtil.CITY_CODE)
    @NotBlank(message = UserRequestUtil.CITY_CODE_EMPTY)
    private String cityCode;
    @JsonProperty(UserRequestUtil.COUNTRY_CODE)
    @NotBlank(message = UserRequestUtil.COUNTRY_CODE_EMPTY)
    private String countryCode;
}
