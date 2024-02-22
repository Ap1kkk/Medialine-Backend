package ru.medialine.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.medialine.dto.CategoryDto;
import ru.medialine.model.Category;
import ru.medialine.repository.SubcategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryConverter {
    private final SubcategoryRepository subcategoryRepository;
    private final SubcategoryConverter subcategoryConverter;

    public CategoryDto convert(Category source) {
        CategoryDto target = new CategoryDto();

        target.setId(source.getId());
        target.setName(source.getName());
        target.setSubcategories(
                subcategoryConverter.convert(subcategoryRepository.findAllByCategoryId(source.getId()))
        );

        return target;
    }

    public List<CategoryDto> convert(List<Category> source) {
        return source.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

}
