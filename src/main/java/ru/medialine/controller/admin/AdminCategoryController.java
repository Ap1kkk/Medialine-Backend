package ru.medialine.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.medialine.model.Category;
import ru.medialine.service.CategoryService;

@Slf4j
@RestController
@RequestMapping("/api/admin/category")
@RequiredArgsConstructor
public class AdminCategoryController {
    private final CategoryService categoryService;

    @PostMapping()
    public Category addCategory(@RequestBody Category category) {
        log.debug("Add category: {}", category);
        return categoryService.addCategory(category);
    }

    @PatchMapping()
    public Category updateCategory(@RequestBody Category category) {
        log.debug("Update category: {}", category);
        return categoryService.updateCategory(category);
    }

    @DeleteMapping()
    @Transactional()
    public void deleteCategory(@RequestParam Long id) {
        log.debug("Delete category with id: {}", id);
        categoryService.deleteCategory(id);
    }
}
