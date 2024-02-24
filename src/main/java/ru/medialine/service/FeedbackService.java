package ru.medialine.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import ru.medialine.config.properties.FeedbackConfigProperties;
import ru.medialine.context.EmailContext;
import ru.medialine.dto.FeedbackDto;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackConfigProperties configProperties;
    private final DefaultEmailService emailService;

    public void send(FeedbackDto feedbackDto) {
        emailService.senEmail(buildEmailContext(feedbackDto));
    }

    private EmailContext buildEmailContext(FeedbackDto feedbackDto) {
        var templateParams = configProperties.getTemplateParams();

        return new EmailContext(
                configProperties.getSender(),
                configProperties.getReceiver(),
                configProperties.getSubject(),
                configProperties.getLanguages(),
                configProperties.getEmailTemplateLocation(),
                Map.of(
                    "clientName", feedbackDto.getClientName(),
                    "clientPhone", feedbackDto.getPhone(),
                    "formattedClientPhone", formatPhone(feedbackDto.getPhone())
                )
        );
    }

    private String formatPhone(String phone) {
        String cleaned = phone.replaceAll("[^0-9]", "");

        if (cleaned.length() == 11 && cleaned.startsWith("7")) {
            return "+7 (" + cleaned.substring(1, 4) + ") " + cleaned.substring(4, 7) + "-" + cleaned.substring(7, 9) + "-" + cleaned.substring(9);
        }

        return phone;
    }
}
