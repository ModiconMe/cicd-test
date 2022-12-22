package com.example.springbootfullstack.student.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static java.lang.String.format;

@Getter
public class ApiException extends RuntimeException {

    private final HttpStatus status;
    private final String message;

    public ApiException(HttpStatus status, String message) {
        this.message = message;
        this.status = status;
    }

    public static ApiException build(HttpStatus status, String message, Object... args) {
        throw new ApiException(status, format(message, args));
    }

}
