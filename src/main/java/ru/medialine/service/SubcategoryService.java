package ru.medialine.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.medialine.converter.SubcategoryConverter;
import ru.medialine.dto.SubcategoryDto;
import ru.medialine.exception.database.AlreadyExistException;
import ru.medialine.exception.database.DatabaseException;
import ru.medialine.exception.database.EntityNotFoundException;
import ru.medialine.model.Product;
import ru.medialine.model.Subcategory;
import ru.medialine.repository.ProductRepository;
import ru.medialine.repository.SubcategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubcategoryService {
    private final SubcategoryRepository subcategoryRepository;
    private final SubcategoryConverter subcategoryConverter;
    private final ProductRepository productRepository;

    public Subcategory tryGetById(Long id) throws EntityNotFoundException {
        return subcategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find subcategory by id " + id));
    }

    public Subcategory addSubcategory(SubcategoryDto subcategoryDto) throws AlreadyExistException {
        if(subcategoryDto.getId() != null) {
            if(subcategoryRepository.findById(subcategoryDto.getId()).isPresent())
                throw new AlreadyExistException("Subcategory with id " + subcategoryDto.getId() + " already exists");
        }

        return subcategoryRepository.save(subcategoryConverter.convert(subcategoryDto));
    }

    public Subcategory updateSubcategory(SubcategoryDto subcategoryDto) throws EntityNotFoundException {
        tryGetById(subcategoryDto.getId());

        return subcategoryRepository.save(subcategoryConverter.convert(subcategoryDto));
    }

    @Transactional
    public void deleteRelatedSubcategories(Long categoryId) {
        List<Subcategory> subcategories = subcategoryRepository.findAllByCategoryId(categoryId);

        for (Subcategory subcategory : subcategories) {
            subcategoryRepository.updateProductSubcategoryToNull(subcategory.getId());
        }

        subcategoryRepository.deleteAll(subcategories);
    }


    @Transactional
    public void deleteSubcategory(Long id) throws EntityNotFoundException {
        tryGetById(id);

        subcategoryRepository.updateProductSubcategoryToNull(id);
        subcategoryRepository.deleteById(id);
    }
}
