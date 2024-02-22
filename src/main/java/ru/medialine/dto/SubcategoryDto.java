package ru.medialine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubcategoryDto {
    private Long id;
    private String name;
    private Long categoryId;
}
