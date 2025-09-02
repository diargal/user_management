package com.nisum.user_management.infrastructure.controller.handler;

import com.nisum.user_management.domain.exception.ExistEmailException;
import com.nisum.user_management.domain.exception.UserNotFoundException;
import com.nisum.user_management.infrastructure.controller.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ExistEmailException.class)
    public ResponseEntity<ErrorResponseDto> handleEmailFormatException(ExistEmailException exception) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(exception.getMessage());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFoundException(UserNotFoundException exception) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(exception.getMessage());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = fieldName + " -> " +error.getDefaultMessage();
            errors.put("mensaje", errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericException(Exception exception) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(exception.getMessage());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}