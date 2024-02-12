package ru.medialine.controller;

import org.springframework.web.bind.annotation.*;
import ru.medialine.model.News;
import ru.medialine.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/all")
    public List<News> getAllNews() {
        log.debug("Get all news");
        return newsService.getAllNews();
    }

    @PostMapping(value = "/add")
    public News addNews(@RequestBody News news) {
        log.debug("Add news: {}", news.toString());
        return newsService.addNews(news);
    }
    @PatchMapping("/update")
    public News updateNews(@RequestBody News news) {
        log.debug("Update news: {}", news.toString());
        return newsService.updateNews(news);
    }

    @DeleteMapping("/delete")
    public void deleteNews(@RequestParam Long id) {
        log.debug("Delete news with id: {}", id);
        newsService.deleteNews(id);
    }
}
