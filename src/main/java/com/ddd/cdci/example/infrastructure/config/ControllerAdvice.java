package com.ddd.cdci.example.infrastructure.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    protected ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }

}
