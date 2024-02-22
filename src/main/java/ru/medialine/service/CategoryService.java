package ru.medialine.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.medialine.converter.CategoryConverter;
import ru.medialine.dto.CategoryDto;
import ru.medialine.exception.DatabaseException;
import ru.medialine.model.Category;
import ru.medialine.model.Product;
import ru.medialine.repository.CategoryRepository;
import ru.medialine.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;
    private final SubcategoryService subcategoryService;

    @Value("${category.defaultId}")
    private Long defaultCategoryId;

    @SneakyThrows
    public Category tryGetById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new DatabaseException("Unable to find category by id " + id));
    }

    public List<CategoryDto> getAll() {
        return categoryConverter.convert(categoryRepository.findAll());
    }

    public Category addCategory(Category category) {
        if(category.getId() != null) {
            if(categoryRepository.findById(category.getId()).isPresent())
                throw new DatabaseException("Category with id " + category.getId() + " already exists");
        }
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category) {
        tryGetById(category.getId());

        return categoryRepository.save(category);
    }

    @Transactional
    public void deleteCategory(Long id) {
        if(id.equals(defaultCategoryId))
            throw new DatabaseException("Cannot delete default category");

        Category category = tryGetById(id);

        categoryRepository.updateProductsCategory(id);
        subcategoryService.deleteRelatedSubcategories(id);
        categoryRepository.deleteById(id);
    }

    public Product setDefaultCategory(Product product) {
        product.setCategory(getDefaultCategory());
        return product;
    }

    public Category getDefaultCategory() {
        return tryGetById(defaultCategoryId);
    }
}
