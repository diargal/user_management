package com.nisum.user_management.infrastructure.util;

public interface UserRequestUtil {
    String REGULAR_EXPRESSION_EMAIL = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    String MESSAGE_EMAIL = "El correo debe seguir una expresión regular para validar que formato sea el correcto.(aaaaaaa@dominio.cl)";
    String PASSWORD_EMPTY = "Password es obligatorio";
    String REGULAR_EXPRESSION_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&_]{7,}$";
    String MESSAGE_EXPRESSION_PASSWORD = "La contraseña debe tener al menos 7 caracteres, contener al menos una " +
            "letra mayúscula, una minúscula, un número y un carácter especial.";
    String MESSAGE = "mensaje";
    String CITY_CODE = "citycode";
    String COUNTRY_CODE = "contrycode";
    String CITY_CODE_EMPTY = "City code es obligatorio";
    String COUNTRY_CODE_EMPTY = "Country code es obligatorio";
    String NUMBER_EMPTY = "Number es obligatorio";
}
