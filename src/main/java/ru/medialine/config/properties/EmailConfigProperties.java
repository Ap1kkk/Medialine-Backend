package ru.medialine.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "email")
@Getter
@Setter
public class EmailConfigProperties {
    private String host;
    private Integer port;
    private String username;
    private String password;
    private String transportProtocol;
    private Boolean smtpAuth;
    private Boolean starttlsEnable;
    private Boolean debug;
}
