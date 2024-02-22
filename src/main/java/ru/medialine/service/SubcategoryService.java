package ru.medialine.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.medialine.converter.SubcategoryConverter;
import ru.medialine.dto.SubcategoryDto;
import ru.medialine.exception.DatabaseException;
import ru.medialine.model.Product;
import ru.medialine.model.Subcategory;
import ru.medialine.repository.CategoryRepository;
import ru.medialine.repository.ProductRepository;
import ru.medialine.repository.SubcategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubcategoryService {
    private final SubcategoryRepository subcategoryRepository;
    private final SubcategoryConverter subcategoryConverter;
    private final ProductRepository productRepository;

    public Subcategory tryGetById(Long id) {
        return subcategoryRepository.findById(id)
                .orElseThrow(() -> new DatabaseException("Unable to find subcategory by id " + id));
    }

    @SneakyThrows
    public Subcategory addSubcategory(SubcategoryDto subcategoryDto) {
        if(subcategoryDto.getId() != null) {
            if(subcategoryRepository.findById(subcategoryDto.getId()).isPresent())
                throw new DatabaseException("Subcategory with id " + subcategoryDto.getId() + " already exists");
        }

        return subcategoryRepository.save(subcategoryConverter.convert(subcategoryDto));
    }

    @SneakyThrows
    public Subcategory updateSubcategory(SubcategoryDto subcategoryDto) {
        tryGetById(subcategoryDto.getId());

        return subcategoryRepository.save(subcategoryConverter.convert(subcategoryDto));
    }

    @SneakyThrows
    @Transactional
    public void deleteRelatedSubcategories(Long categoryId) {
        List<Subcategory> subcategories = subcategoryRepository.findAllByCategoryId(categoryId);

        for (Subcategory subcategory : subcategories) {
            subcategoryRepository.updateProductSubcategoryToNull(subcategory.getId());
        }

        subcategoryRepository.deleteAll(subcategories);
    }

    @SneakyThrows
    @Transactional
    public void deleteSubcategory(Long id) {
        tryGetById(id);

        subcategoryRepository.updateProductSubcategoryToNull(id);
        subcategoryRepository.deleteById(id);
    }

    public Product setDefaultSubcategory(Product product) {
        product.setSubcategory(null);
        return product;
    }
}
