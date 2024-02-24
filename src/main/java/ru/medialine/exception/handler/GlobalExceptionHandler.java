package ru.medialine.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import ru.medialine.exception.AppException;

import java.nio.file.NoSuchFileException;

@ControllerAdvice
public class GlobalExceptionHandler extends BaseExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<AppException> catchException(Exception e) {
        return buildAppException(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<AppException> catchNoSuchFileException(NoSuchFileException e) {
        return buildAppException("No such file: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<AppException> catchResourceException(NoResourceFoundException e) {
        return buildAppException(e, HttpStatus.NOT_FOUND);
    }
}