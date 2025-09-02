package com.nisum.user_management.domain.model;

import lombok.Data;

@Data
public class Phone {
    private String number;
    private String cityCode;
    private String countryCode;

    public Phone(String number, String cityCode, String countryCode) {
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
    }
}
