package ru.medialine.exception;

import lombok.Getter;

@Getter
public class DatabaseException extends RuntimeException {

    private static final String prefix = "Database Error: ";

    public DatabaseException(String message) {
        super(prefix + message);
    }

    public DatabaseException(String message, Throwable cause) {
        super(prefix + message, cause);
    }
}
