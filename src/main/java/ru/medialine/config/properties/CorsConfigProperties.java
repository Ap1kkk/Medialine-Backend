package ru.medialine.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "cors")
@Getter
@Setter
public class CorsConfigProperties {
    private String allowedOrigin;
    private String allowedHeader;
    private String allowedMethod;
    private String configurationPattern;
}
