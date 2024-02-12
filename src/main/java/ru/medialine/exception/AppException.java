package ru.medialine.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@Data
@AllArgsConstructor
public class AppException {
    private HttpStatus status;
    private String message;
}
