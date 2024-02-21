package ru.medialine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.medialine.model.Category;
import ru.medialine.model.Subcategory;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private Long categoryId;
    private Long subcategoryId;
    private String description;
    private String[] specials;
    private String[] packaging;
    private String imagePath;
}
