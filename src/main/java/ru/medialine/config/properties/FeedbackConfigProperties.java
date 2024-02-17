package ru.medialine.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component
@ConfigurationProperties(prefix = "feedback")
@Getter
@Setter
public class FeedbackConfigProperties {
    private String sender;
    private String receiver;
    private String subject;
    private String emailTemplateLocation;
    private String[] languages;
    private Map<String, Object> templateParams;
}
