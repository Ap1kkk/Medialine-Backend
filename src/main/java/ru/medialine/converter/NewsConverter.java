package ru.medialine.converter;


import org.springframework.stereotype.Component;
import ru.medialine.dto.NewsDto;
import ru.medialine.model.News;

@Component
public class NewsConverter {
//    @Override
    public News convert(NewsDto source) {
        News target = new News();

        target.setId(source.getId());
        target.setTitle(source.getTitle());
        target.setTime(source.getTime());
        target.setText(source.getText());
        target.setImagePath(source.getImagePath());
        return target;
    }
}
