package ru.medialine.exception.database;

import lombok.Getter;

@Getter
public class AlreadyExistException extends DatabaseException {
    public AlreadyExistException(String message) {
        super(message);
    }

    public AlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
