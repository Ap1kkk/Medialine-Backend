package ru.medialine.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.medialine.dto.SubcategoryDto;
import ru.medialine.exception.database.DatabaseException;
import ru.medialine.model.Subcategory;
import ru.medialine.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SubcategoryConverter {
    private final CategoryRepository categoryRepository;

    public SubcategoryDto convert(Subcategory source) {
        SubcategoryDto target = new SubcategoryDto();

        target.setId(source.getId());
        target.setName(source.getName());
        target.setCategoryId(source.getCategory().getId());
//        target.setCategoryId(categoryRepository
//                .findById(source.getCategory().getId())
//                .orElseThrow(() -> new DatabaseException("Unable to find category with id: " + source.getCategory().getId()))
//                .getId());

        return target;
    }

    public Subcategory convert(SubcategoryDto source) {
        Subcategory target = new Subcategory();

        target.setId(source.getId());
        target.setName(source.getName());
        target.setCategory(categoryRepository
                .findById(source.getCategoryId())
                .orElseThrow(() -> new DatabaseException("Unable to find category with id: " + source.getCategoryId())));

        return target;
    }

    public List<SubcategoryDto> convert(List<Subcategory> source) {
        return source.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
