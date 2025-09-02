package com.nisum.user_management.domain.model.enums;

import lombok.Getter;

@Getter
public enum ErrorMessageEnum {
    EMAIL_EXIST_EXCEPTION("El email ya se encuentra registrado."),
    EMAIL_FORMAT_EXCEPTION("No se cumple con el formato de correo."),
    USER_NOT_FOUND_EXCEPTION("No se encontró registro."),
    LOGIN_ERROR_BY_USER_NOT_FOUND("El usuario %s no fue encontrado."),
    LOGIN_ERROR_BY_CREDENTIALS("Credenciales incorrectas para hacer login."),
    LOGIN_DEFAULT_ERROR("Se presentó un problema al realizar el login."),
    VALUE_NULL_OR_EMPTY_EXCEPTION("Valor vacío o nulo: ");
    private final String message;

    ErrorMessageEnum(String message) {
        this.message = message;
    }
}
