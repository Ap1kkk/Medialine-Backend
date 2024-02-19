package ru.medialine.service;

import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;
import ru.medialine.exception.DatabaseException;
import ru.medialine.model.News;
import ru.medialine.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;
    private final FileService fileService;

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    @SneakyThrows
    public News getById(Long id) {
        return newsRepository.findById(id)
                .orElseThrow(() -> new DatabaseException("Unable to find news by id " + id));
    }

    @SneakyThrows
    public News addNews(News news, MultipartFile file) {
        if(news.getId() != null) {
            if(newsRepository.findById(news.getId()).isPresent())
                throw new DatabaseException("News with id " + news.getId() + " already exists");
        }

        if(file != null) {
            String imagePath = fileService.upload(file);
            news.setImagePath(imagePath);
        }

        return newsRepository.save(news);
    }

    @SneakyThrows
    public News updateNews(News news, MultipartFile file) {
        getById(news.getId());

        if(file != null) {
            String oldPath = news.getImagePath();
            String imagePath = fileService.upload(file, oldPath);
            news.setImagePath(imagePath);
        }

        return newsRepository.save(news);
    }

    @SneakyThrows
    public void deleteNews(Long id) {
        getById(id);
        newsRepository.deleteById(id);
    }

}
