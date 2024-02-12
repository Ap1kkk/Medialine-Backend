package ru.medialine.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        try {
            log.debug("Get all news");
            return newsService.getAllNews();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping("/{id}")
    public News getNewsById(@PathVariable Long id) {
        try {
            log.debug("Get news by id: {}", id);
            return newsService.getById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @PostMapping(value = "/add")
    public News addNews(@RequestBody News news) {
        try {
            log.debug("Add news: {}", news.toString());
            return newsService.addNews(news);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
    @PatchMapping("/update")
    public ResponseEntity<?> updateNews(@RequestBody News news) {
        try {
            log.debug("Update news: {}", news.toString());
            return new ResponseEntity<>(newsService.updateNews(news), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/delete")
    public void deleteNews(@RequestParam Long id) {
        try {
            log.debug("Delete news with id: {}", id);
            newsService.deleteNews(id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
