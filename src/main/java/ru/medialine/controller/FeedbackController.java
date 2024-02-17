package ru.medialine.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.medialine.dto.FeedbackDto;
import ru.medialine.model.News;
import ru.medialine.service.EmailServiceImplementation;

@Slf4j
@RestController
@RequestMapping("/api/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final EmailServiceImplementation emailService;

    @PostMapping()
    public void sendFeedback(@RequestBody FeedbackDto feedback) {
        log.debug("Feedback needed for: {}", feedback.toString());
        emailService.sendFeedbackMessage(feedback);
    }
}
