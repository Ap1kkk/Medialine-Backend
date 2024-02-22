package ru.medialine.service;

import lombok.SneakyThrows;
import ru.medialine.converter.NewsConverter;
import ru.medialine.dto.NewsDto;
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
    private final NewsConverter newsConverter;

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    @SneakyThrows
    public News tryGetById(Long id) {
        return newsRepository.findById(id)
                .orElseThrow(() -> new DatabaseException("Unable to find news by id " + id));
    }

    @SneakyThrows
    public News addNews(NewsDto newsDto) {
        if(newsDto.getId() != null) {
            if(newsRepository.findById(newsDto.getId()).isPresent())
                throw new DatabaseException("News with id " + newsDto.getId() + " already exists");
        }

        News news = newsConverter.convert(newsDto);

        if(newsDto.getImage() != null) {
            String imagePath = fileService.upload(newsDto.getImage());
            news.setImagePath(imagePath);
        }

        return newsRepository.save(news);
    }

    @SneakyThrows
    public News updateNews(NewsDto newsDto) {
        tryGetById(newsDto.getId());

        News news = newsConverter.convert(newsDto);

        if(newsDto.getImage() != null) {
            String oldPath = newsDto.getImagePath();
            String imagePath = fileService.upload(newsDto.getImage(), oldPath);
            news.setImagePath(imagePath);
        }

        return newsRepository.save(news);
    }

    @SneakyThrows
    public void deleteNews(Long id) {
        tryGetById(id);
        newsRepository.deleteById(id);
    }

}
