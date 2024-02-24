package ru.medialine.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.medialine.dto.CredentialsDto;
import ru.medialine.service.AuthService;

@Slf4j
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody CredentialsDto request) {
        log.debug("Try to log in by: {}", request.getEmail());
        return authService.authenticate(request);
    }



}
