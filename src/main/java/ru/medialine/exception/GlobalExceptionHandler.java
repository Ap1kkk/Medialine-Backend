package ru.medialine.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<AppException> catchResourceNotFoundException(Exception e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new AppException(HttpStatus.NOT_FOUND, e.getMessage()), HttpStatus.NOT_FOUND);
    }
}