package ru.medialine.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.medialine.dto.LoginDto;
import ru.medialine.model.News;
import ru.medialine.model.Product;
import ru.medialine.service.AuthService;
import ru.medialine.service.NewsService;
import ru.medialine.service.ProductService;

@Slf4j
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginDto request) {
        return authService.authenticate(request);
    }



}
