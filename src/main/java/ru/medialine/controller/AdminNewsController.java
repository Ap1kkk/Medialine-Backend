package ru.medialine.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.medialine.model.News;
import ru.medialine.service.NewsService;

@Slf4j
@RestController
@RequestMapping("/api/admin/news")
@RequiredArgsConstructor
public class AdminNewsController {

    private final NewsService newsService;
    private static final String fileParamName = "image";
    @PostMapping()
    public News addNews(@ModelAttribute News news, @RequestPart(fileParamName) MultipartFile file) {
        log.debug("Add news: {}", news.toString());
        return newsService.addNews(news, file);
    }
    @PatchMapping()
    public News updateNews(@ModelAttribute News news, @RequestPart(fileParamName) MultipartFile file) {
        log.debug("Update news: {}", news.toString());
        return newsService.updateNews(news, file);
    }

    @DeleteMapping()
    public void deleteNews(@RequestParam Long id) {
        log.debug("Delete news with id: {}", id);
        newsService.deleteNews(id);
    }

}
