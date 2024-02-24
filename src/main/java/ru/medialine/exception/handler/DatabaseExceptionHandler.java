package ru.medialine.exception.handler;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.medialine.exception.AppException;
import ru.medialine.exception.database.DatabaseException;
import ru.medialine.exception.database.EntityNotFoundException;

@ControllerAdvice
public class DatabaseExceptionHandler extends BaseExceptionHandler {
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<AppException> catchException(DataAccessException e) {
        return buildAppException("Data access error", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<AppException> catchDatabaseException(DatabaseException e) {
        return buildAppException(e, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<AppException> cathException(EntityNotFoundException e) {
        return buildAppException(e, HttpStatus.NOT_FOUND);
    }
}
