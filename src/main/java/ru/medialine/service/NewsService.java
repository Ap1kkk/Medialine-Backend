package ru.medialine.service;

import lombok.SneakyThrows;
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

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    @SneakyThrows
    public News getById(Long id) {
        return newsRepository.findById(id)
                .orElseThrow(() -> new DatabaseException("Unable to find news by id " + id));
    }

    @SneakyThrows
    public News addNews(News news) {
        if(news.getId() != null) {
            if(newsRepository.findById(news.getId()).isPresent())
                throw new DatabaseException("News with id " + news.getId() + " already exists");
        }
        return newsRepository.save(news);
    }

    @SneakyThrows
    public News updateNews(News news) {
        getById(news.getId());
        return newsRepository.save(news);
    }

    @SneakyThrows
    public void deleteNews(Long id) {
        getById(id);
        newsRepository.deleteById(id);
    }

}
