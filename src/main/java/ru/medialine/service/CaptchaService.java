package ru.medialine.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.medialine.config.properties.CaptchaConfigProperties;
import ru.medialine.dto.RecaptchaRequestDto;
import ru.medialine.dto.RecaptchaResponse;
import ru.medialine.exception.RecaptchaException;

@Slf4j
@Service
@RequiredArgsConstructor
public class CaptchaService {
    public final CaptchaConfigProperties captchaProperties;

    @SneakyThrows
    public RecaptchaResponse verify(RecaptchaRequestDto recaptchaRequestDto) {
        String url = captchaProperties.getUrl();
        url += "?secret=" + captchaProperties.getSecret() + "&response=" + recaptchaRequestDto.getCaptchaToken();

        RestTemplate restTemplate = new RestTemplate();

        RecaptchaResponse recaptchaResponse = restTemplate.getForObject(url, RecaptchaResponse.class);

        if(recaptchaResponse == null || !recaptchaResponse.isSuccess()) {
            throw new RecaptchaException(recaptchaResponse);
        }

        return recaptchaResponse;
    }
}
