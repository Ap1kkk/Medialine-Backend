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
    public Category addCategory(@ModelAttribute Category category) {
        return categoryService.addCategory(category);
    }

    @PatchMapping()
    public Category updateCategory(@ModelAttribute Category category) {
        return categoryService.updateCategory(category);
    }

    @DeleteMapping()
    @Transactional()
    public void deleteCategory(@RequestParam Long id) {
        categoryService.deleteCategory(id);
    }
}
