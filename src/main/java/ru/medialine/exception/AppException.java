package ru.medialine.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
@AllArgsConstructor
public class AppException {
    private HttpStatus status;
    private String message;
}
