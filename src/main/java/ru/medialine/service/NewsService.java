package ru.medialine.service;

import lombok.SneakyThrows;
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
    public News addNews(News news) {
        if(news.getId() != null) {
            if(newsRepository.findById(news.getId()).isPresent())
                throw new Exception("News with id " + news.getId() + " already exists");
        }
        return newsRepository.save(news);
    }

    @SneakyThrows
    public News updateNews(News news) {
        findById(news.getId());
        return newsRepository.save(news);
    }

    @SneakyThrows
    public void deleteNews(Long id) {
        findById(id);
        newsRepository.deleteById(id);
    }

    @SneakyThrows
    public News findById(Long id) {
        return newsRepository.findById(id)
                .orElseThrow(() -> new Exception("Unable to find news by id " + id));
    }
}
