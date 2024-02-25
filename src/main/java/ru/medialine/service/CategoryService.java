package ru.medialine.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.medialine.converter.CategoryConverter;
import ru.medialine.dto.CategoryDto;
import ru.medialine.exception.database.AlreadyExistException;
import ru.medialine.exception.database.DatabaseException;
import ru.medialine.exception.database.EntityNotFoundException;
import ru.medialine.model.Category;
import ru.medialine.model.Product;
import ru.medialine.repository.CategoryRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;
    private final SubcategoryService subcategoryService;

    @Value("${category.defaultId}")
    private Long defaultCategoryId;

    public Category tryGetById(Long id) throws EntityNotFoundException {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find category by id " + id));
    }

    public List<CategoryDto> getAll() {
        return categoryConverter.convert(categoryRepository.findAll());
    }

    public Category addCategory(Category category) throws AlreadyExistException {
        log.debug("Try to add category: {}", category);

        if(category.getId() != null) {
            if(categoryRepository.findById(category.getId()).isPresent())
                throw new AlreadyExistException("Category with id " + category.getId() + " already exists");
        }
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category) throws EntityNotFoundException {
        log.debug("Try to update category: {}", category);

        tryGetById(category.getId());

        return categoryRepository.save(category);
    }

    @Transactional
    public void deleteCategory(Long id) {
        log.debug("Try to delete category by id: {}", id);

        if(id.equals(defaultCategoryId))
            throw new DatabaseException("Cannot delete default category");

        Category category = tryGetById(id);

        categoryRepository.updateProductsCategory(id);
        subcategoryService.deleteRelatedSubcategories(id);
        categoryRepository.deleteById(id);
    }
}
