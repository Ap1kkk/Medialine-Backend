package ru.medialine.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.medialine.dto.SubcategoryDto;
import ru.medialine.model.Category;
import ru.medialine.model.Subcategory;
import ru.medialine.service.SubcategoryService;

@Slf4j
@RestController
@RequestMapping("/api/admin/subcategory")
@RequiredArgsConstructor
public class AdminSubcategoryController {
    private final SubcategoryService subcategoryService;

    @PostMapping()
    public Subcategory addCategory(@ModelAttribute SubcategoryDto subcategoryDto) {
        return subcategoryService.addSubcategory(subcategoryDto);
    }

    @PatchMapping()
    public Subcategory updateCategory(@ModelAttribute SubcategoryDto subcategoryDto) {
        return subcategoryService.updateSubcategory(subcategoryDto);
    }

    @DeleteMapping()
    @Transactional()
    public void deleteCategory(@RequestParam Long id) {
        subcategoryService.deleteSubcategory(id);
    }
}
