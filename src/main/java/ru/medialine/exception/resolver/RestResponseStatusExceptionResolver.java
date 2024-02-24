package ru.medialine.exception.resolver;

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
import ru.medialine.exception.AppException;

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

        try (PrintWriter writer = response.getWriter()) {
            ObjectMapper objectMapper = new ObjectMapper();
            log.error(exception.getMessage());
            String json = objectMapper.writeValueAsString(new AppException(HttpStatus.FORBIDDEN, exception.getMessage()));
            writer.write(json);
        } catch (IOException ioException) {
            log.error("Error writing JSON response", ioException);
        }

        return new ModelAndView(new MappingJackson2JsonView());
    }

}