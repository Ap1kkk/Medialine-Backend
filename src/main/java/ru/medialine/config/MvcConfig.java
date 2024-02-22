package ru.medialine.config;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${resources.path}")
    private String pathPattern;
    @Value("${resources.resourceLocation}")
    private String location;
    @Value("${format.dateFormat}")
    private String dateFormat;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(pathPattern)
                .addResourceLocations("file:" + System.getProperty("user.dir") + location);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatterForFieldType(LocalDate.class, new LocalDateFormatter());
    }

    private class LocalDateFormatter implements org.springframework.format.Formatter<LocalDate> {
        @Override
        @SneakyThrows
        public LocalDate parse(String text, Locale locale){
            return LocalDate.parse(text, DateTimeFormatter.ofPattern(dateFormat));
        }

        @Override
        public String print(LocalDate object, Locale locale) {
            return object.toString();
        }
    }
}
