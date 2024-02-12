package ru.medialine.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.medialine.model.News;
import ru.medialine.service.NewsService;

@Slf4j
@RestController
@RequestMapping("/api/admin/news")
@RequiredArgsConstructor
public class AdminNewsController {

    private final NewsService newsService;

    @PostMapping()
    public News addNews(@RequestBody News news) {
        log.debug("Add news: {}", news.toString());
        return newsService.addNews(news);
    }
    @PatchMapping()
    public News updateNews(@RequestBody News news) {
        log.debug("Update news: {}", news.toString());
        return newsService.updateNews(news);
    }

    @DeleteMapping()
    public void deleteNews(@RequestParam Long id) {
        log.debug("Delete news with id: {}", id);
        newsService.deleteNews(id);
    }

}
