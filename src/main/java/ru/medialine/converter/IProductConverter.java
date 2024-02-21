package ru.medialine.converter;

import ru.medialine.dto.ProductDto;
import ru.medialine.model.Product;

public interface IProductConverter {
    public Product convert(ProductDto source);
    public ProductDto convert(Product source);
}
