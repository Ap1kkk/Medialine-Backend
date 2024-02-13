package ru.medialine.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.ErrorResponse;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@Component
public class RestResponseStatusExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(
            HttpServletRequest request,
            HttpServletResponse response,
            Object object,
            Exception exception) {
        log.error(exception.getMessage());

        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType("application/json");

        try (PrintWriter writer = response.getWriter()) {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(new AppException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage()));
            writer.write(json);
        } catch (IOException ioException) {
            log.error("Error writing JSON response", ioException);
        }

        return new ModelAndView(new MappingJackson2JsonView());
    }

}