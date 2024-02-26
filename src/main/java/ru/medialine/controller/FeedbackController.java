package ru.medialine.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.medialine.dto.FeedbackDto;
import ru.medialine.dto.RecaptchaRequestDto;
import ru.medialine.dto.RecaptchaResponse;
import ru.medialine.service.CaptchaService;
import ru.medialine.service.FeedbackService;

@Slf4j
@RestController
@RequestMapping("/api/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;
    private final CaptchaService captchaService;

    @PostMapping()
    public void sendFeedback(@ModelAttribute FeedbackDto feedback, @ModelAttribute RecaptchaRequestDto recaptchaRequestDto) {
        captchaService.verify(recaptchaRequestDto);
        feedbackService.send(feedback);
    }
}
