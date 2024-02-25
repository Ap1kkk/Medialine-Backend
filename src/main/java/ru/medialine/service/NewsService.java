package ru.medialine.service;

import lombok.SneakyThrows;
import ru.medialine.converter.NewsConverter;
import ru.medialine.dto.NewsDto;
import ru.medialine.exception.database.AlreadyExistException;
import ru.medialine.exception.database.DatabaseException;
import ru.medialine.exception.database.EntityNotFoundException;
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

    public News tryGetById(Long id) throws EntityNotFoundException {
        return newsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find news by id " + id));
    }

    public News addNews(NewsDto newsDto) throws AlreadyExistException {
        log.debug("Try to add news: {}", newsDto);

        if(newsDto.getId() != null) {
            if(newsRepository.findById(newsDto.getId()).isPresent())
                throw new AlreadyExistException("News with id " + newsDto.getId() + " already exists");
        }

        News news = newsConverter.convert(newsDto);

        if(newsDto.getImage() != null) {
            String imagePath = fileService.upload(newsDto.getImage());
            news.setImagePath(imagePath);
        }

        return newsRepository.save(news);
    }

    public News updateNews(NewsDto newsDto) throws EntityNotFoundException  {
        log.debug("Try to update news: {}", newsDto);

        tryGetById(newsDto.getId());

        News news = newsConverter.convert(newsDto);

        if(newsDto.getImage() != null) {
            String oldPath = newsDto.getImagePath();
            String imagePath = fileService.upload(newsDto.getImage(), oldPath);
            news.setImagePath(imagePath);
        }

        return newsRepository.save(news);
    }

    public void deleteNews(Long id) throws EntityNotFoundException {
        log.debug("Try to delete news by id: {}", id);

        tryGetById(id);
        newsRepository.deleteById(id);
    }

}
