package ru.medialine.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.medialine.converter.ProductConverter;
import ru.medialine.dto.ProductDto;
import ru.medialine.exception.DatabaseException;
import ru.medialine.model.Category;
import ru.medialine.model.Product;
import ru.medialine.repository.ProductRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final FileService fileService;
    private final ProductConverter productConverter;
    private final CategoryService categoryService;
    private final SubcategoryService subcategoryService;

    @Value("${category.defaultId}")
    private Long defaultCategoryId;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @SneakyThrows
    public Product tryGetById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new DatabaseException("Unable to find product by id " + id));
    }
    @SneakyThrows
    public Product addProduct(ProductDto productDto) {
        if(productDto.getId() != null) {
            if(productRepository.findById(productDto.getId()).isPresent())
                throw new DatabaseException("Product with id " + productDto.getId() + " already exists");
        }

        checkForDefaultCategory(productDto);

        Product product = productConverter.convert(productDto);

        if(productDto.getImage() != null) {
            String imagePath = fileService.upload(productDto.getImage());
            product.setImagePath(imagePath);
        }

        return productRepository.save(product);
    }
    @SneakyThrows
    public Product updateProduct(ProductDto productDto) {
        tryGetById(productDto.getId());

        checkForDefaultCategory(productDto);

        Product product = productConverter.convert(productDto);

        if(productDto.getImage() != null) {
            String oldPath = product.getImagePath();
            String imagePath = fileService.upload(productDto.getImage(), oldPath);
            product.setImagePath(imagePath);
        }

        return productRepository.save(product);
    }

    @SneakyThrows
    public void deleteProduct(Long id) {
        tryGetById(id);
        productRepository.deleteById(id);
    }

    @SneakyThrows
    public void checkForDefaultCategory(ProductDto productDto) {
        if(productDto.getCategoryId() != null) {
            if(productDto.getCategoryId().equals(defaultCategoryId) && productDto.getSubcategoryId() != null)
                throw new Exception("Product with default category must not have subcategory");
        }
    }
}
