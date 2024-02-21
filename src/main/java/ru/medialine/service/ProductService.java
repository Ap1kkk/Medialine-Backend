package ru.medialine.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.medialine.converter.ProductConverter;
import ru.medialine.dto.ProductDto;
import ru.medialine.exception.DatabaseException;
import ru.medialine.model.Product;
import ru.medialine.repository.ProductRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final FileService fileService;
    private final IMedialineConversionService conversionService;
    private final ProductConverter productConverter;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @SneakyThrows
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new DatabaseException("Unable to find product by id " + id));
    }
    @SneakyThrows
    public Product addProduct(ProductDto product, MultipartFile file) {

        return productConverter.convert(product);
    }

    @SneakyThrows
    public Product addProduct(Product product, MultipartFile file) {
        if(product.getId() != null) {
            if(productRepository.findById(product.getId()).isPresent())
                throw new DatabaseException("Product with id " + product.getId() + " already exists");
        }

        if(file != null) {
            String imagePath = fileService.upload(file);
            product.setImagePath(imagePath);
        }

        return productRepository.save(product);
    }
    @SneakyThrows
    public Product updateProduct(Product product, MultipartFile file) {
        getById(product.getId());

        if(file != null) {
            String oldPath = product.getImagePath();
            String imagePath = fileService.upload(file, oldPath);
            product.setImagePath(imagePath);
        }

        return productRepository.save(product);
    }

    @SneakyThrows
    public void deleteProduct(Long id) {
        getById(id);
        productRepository.deleteById(id);
    }

}
