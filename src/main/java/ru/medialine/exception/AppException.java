package ru.medialine.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
public class AppException {
    private int code;
    private HttpStatus status;
    private String message;

    public AppException(HttpStatus status, String message) {
        this.message = message;
        this.status = status;
        code = status.value();
    }
}
