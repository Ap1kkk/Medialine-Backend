package ru.medialine.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "feedback")
@Getter
@Setter
public class FeedbackConfigProperties {
    private String from;
    private String to;
    private String subject;
    private String textPattern;
}
