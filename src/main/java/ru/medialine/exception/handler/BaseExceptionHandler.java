package ru.medialine.exception.handler;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.medialine.exception.AppException;

@Slf4j
public abstract class BaseExceptionHandler {

    protected ResponseEntity<AppException> buildAppException(Exception e, HttpStatus status) {
        return buildAppException(e.getMessage(), status);
    }

    protected ResponseEntity<AppException> buildAppException(String message, HttpStatus status) {
        log.error(message);
        return new ResponseEntity<>(new AppException(status, message), status);
    }
}
