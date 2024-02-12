package ru.medialine.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

@Getter
public class JwtAuthenticationException extends AuthenticationException {

    private final HttpStatus httpStatus;

    public JwtAuthenticationException(String msg, HttpStatus httpStatus, Throwable throwable) {
        super(msg, throwable);
        this.httpStatus = httpStatus;
    }
}
