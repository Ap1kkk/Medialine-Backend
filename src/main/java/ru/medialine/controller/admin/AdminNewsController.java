package ru.medialine.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.medialine.dto.NewsDto;
import ru.medialine.model.News;
import ru.medialine.service.NewsService;

@Slf4j
@RestController
@RequestMapping("/api/admin/news")
@RequiredArgsConstructor
public class AdminNewsController {

    private final NewsService newsService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public News addNews(NewsDto news) {
        return newsService.addNews(news);
    }

    @PatchMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public News updateNews(NewsDto news) {
        return newsService.updateNews(news);
    }

    @DeleteMapping()
    public void deleteNews(@RequestParam Long id) {
        newsService.deleteNews(id);
    }

}
