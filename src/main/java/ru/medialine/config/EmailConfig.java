package ru.medialine.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import ru.medialine.config.properties.EmailConfigProperties;

import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class EmailConfig {

    private final EmailConfigProperties configProperties;
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(configProperties.getHost());
        mailSender.setPort(configProperties.getPort());

        mailSender.setUsername(configProperties.getUsername());
        mailSender.setPassword(configProperties.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", configProperties.getTransportProtocol());
        props.put("mail.smtp.auth", configProperties.getSmtpAuth());
        props.put("mail.smtp.starttls.enable", configProperties.getStarttlsEnable());
        props.put("mail.debug", configProperties.getDebug());

        return mailSender;
    }
}
