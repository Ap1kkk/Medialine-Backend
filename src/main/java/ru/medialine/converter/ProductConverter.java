package ru.medialine.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.medialine.dto.ProductDto;
import ru.medialine.model.Product;
import ru.medialine.repository.CategoryRepository;
import ru.medialine.repository.SubcategoryRepository;
import ru.medialine.service.CategoryService;
import ru.medialine.service.SubcategoryService;

@Component
@RequiredArgsConstructor
public class ProductConverter implements IProductConverter{

    private final CategoryService categoryService;
    private final SubcategoryService subcategoryService;

    @Override
    public Product convert(ProductDto source) {
        Product target = new Product();

        target.setId(source.getId());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());
        target.setImagePath(source.getImagePath());
        target.setSpecials(source.getSpecials());
        target.setPackaging(source.getPackaging());

        target.setCategory(categoryService.tryGetById(source.getCategoryId()));

        if(source.getSubcategoryId() != null) {
            target.setSubcategory(subcategoryService.tryGetById(source.getSubcategoryId()));
        }
        return target;
    }

    @Override
    public ProductDto convert(Product source) {
        ProductDto target = new ProductDto();

        target.setId(source.getId());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());
        target.setSpecials(source.getSpecials());
        target.setPackaging(source.getPackaging());

        if(source.getCategory() != null){
            target.setCategoryId(source.getCategory().getId());
        }
        if(source.getSubcategory() != null) {
            target.setSubcategoryId(source.getSubcategory().getId());
        }

        return target;
    }


}
