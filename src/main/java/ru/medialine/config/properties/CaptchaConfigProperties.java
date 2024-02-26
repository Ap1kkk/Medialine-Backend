package ru.medialine.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "google.recaptcha")
@Getter
@Setter
public class CaptchaConfigProperties {
    private String secret;
    private String url;
    private double scoreThreshold;
}
