package ru.medialine.exception;

import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.nio.file.NoSuchFileException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<AppException> catchException(Exception e) {
        return buildAppException(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<AppException> catchException(DataAccessException e) {
        return buildAppException("Data access error", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<AppException> catchException(JwtException e) {
        return buildAppException(e, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler
    public ResponseEntity<AppException> catchDatabaseException(DatabaseException e) {
        return buildAppException(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<AppException> catchNoSuchFileException(NoSuchFileException e) {
        return buildAppException("No such file: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<AppException> catchResourceException(NoResourceFoundException e) {
        return buildAppException(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(JwtAuthenticationException.class)
    public ResponseEntity<AppException> catchJwtAuthenticationException(JwtAuthenticationException e) {
        return buildAppException(e, e.getHttpStatus());
    }

    private ResponseEntity<AppException> buildAppException(Exception e, HttpStatus status) {
        return buildAppException(e.getMessage(), status);
    }

    private ResponseEntity<AppException> buildAppException(String message, HttpStatus status) {
        log.error(message);
        return new ResponseEntity<>(new AppException(status, message), status);
    }
}