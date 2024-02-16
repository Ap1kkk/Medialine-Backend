package ru.medialine.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.medialine.dto.FeedbackDto;

@Service
@RequiredArgsConstructor
public class EmailServiceImplementation {

    @Value("${feedback.from}")
    private String from;
    @Value("${feedback.subject}")
    private String subject;
    @Value("${feedback.textPattern}")
    private String messagePattern;

    private String to;
    private final JavaMailSender emailSender;

    public void sendFeedbackMessage(FeedbackDto feedbackDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);

        String text = messagePattern
                .replace("[clientName]", feedbackDto.getClientName())
                .replace("[clientPhone]", feedbackDto.getPhone());
        message.setText(text);
        emailSender.send(message);
    }
}
