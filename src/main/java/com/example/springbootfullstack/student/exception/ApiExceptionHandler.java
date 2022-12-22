package com.example.springbootfullstack.student.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Objects;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<Object> handleApiRequestException(ApiException e) {
        return new ResponseEntity<>(new ExceptionDto(e.getStatus(), e.getMessage()), e.getStatus());
    }

    /**
     * Spting validation exception handling
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleApiRequestValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(
                new ExceptionDto(HttpStatus.BAD_REQUEST, Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

}
