package ru.medialine.exception.handler;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.medialine.exception.AppException;
import ru.medialine.exception.JwtAuthenticationException;

@ControllerAdvice
public class AuthenticationExceptionHandler extends BaseExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<AppException> catchException(JwtException e) {
        return buildAppException(e, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<AppException> catchException(AuthenticationException e) {
        return buildAppException(e, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(JwtAuthenticationException.class)
    public ResponseEntity<AppException> catchJwtAuthenticationException(JwtAuthenticationException e) {
        return buildAppException(e, e.getHttpStatus());
    }
}
