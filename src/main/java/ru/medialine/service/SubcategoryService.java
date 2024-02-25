package ru.medialine.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.medialine.converter.SubcategoryConverter;
import ru.medialine.dto.SubcategoryDto;
import ru.medialine.exception.database.AlreadyExistException;
import ru.medialine.exception.database.EntityNotFoundException;
import ru.medialine.model.Subcategory;
import ru.medialine.repository.SubcategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubcategoryService {
    private final SubcategoryRepository subcategoryRepository;
    private final SubcategoryConverter subcategoryConverter;

    public Subcategory tryGetById(Long id) throws EntityNotFoundException {
        return subcategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find subcategory by id " + id));
    }

    public Subcategory addSubcategory(SubcategoryDto subcategoryDto) throws AlreadyExistException {
        log.debug("Try to add subcategory: {}", subcategoryDto);

        if(subcategoryDto.getId() != null) {
            if(subcategoryRepository.findById(subcategoryDto.getId()).isPresent())
                throw new AlreadyExistException("Subcategory with id " + subcategoryDto.getId() + " already exists");
        }

        return subcategoryRepository.save(subcategoryConverter.convert(subcategoryDto));
    }

    public Subcategory updateSubcategory(SubcategoryDto subcategoryDto) throws EntityNotFoundException {
        log.debug("Try to update subcategory: {}", subcategoryDto);

        tryGetById(subcategoryDto.getId());

        return subcategoryRepository.save(subcategoryConverter.convert(subcategoryDto));
    }

    @Transactional
    public void deleteRelatedSubcategories(Long categoryId) {
        log.debug("Try to delete related subcategories to categoryId: {}", categoryId);

        List<Subcategory> subcategories = subcategoryRepository.findAllByCategoryId(categoryId);

        for (Subcategory subcategory : subcategories) {
            subcategoryRepository.updateProductSubcategoryToNull(subcategory.getId());
        }

        subcategoryRepository.deleteAll(subcategories);
    }


    @Transactional
    public void deleteSubcategory(Long id) throws EntityNotFoundException {
        log.debug("Try to delete subcategory with id: {}", id);

        tryGetById(id);

        subcategoryRepository.updateProductSubcategoryToNull(id);
        subcategoryRepository.deleteById(id);
    }

    @SneakyThrows
    public void checkSubcategoryRelation(Long subcategoryId, Long categoryId) {
        Subcategory subcategory = tryGetById(subcategoryId);
        if(!subcategory.getCategory().getId().equals(categoryId)) {
            throw new Exception("Subcategory does not match category: | existing: " + subcategory + " | given categoryId:" + categoryId);
        }
    }
}
