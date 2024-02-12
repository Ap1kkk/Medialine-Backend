package ru.medialine.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.medialine.exception.DatabaseException;
import ru.medialine.model.Product;
import ru.medialine.repository.ProductRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @SneakyThrows
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new DatabaseException("Unable to find product by id " + id));
    }

    @SneakyThrows
    public Product addProduct(Product product) {
        if(product.getId() != null) {
            if(productRepository.findById(product.getId()).isPresent())
                throw new DatabaseException("Product with id " + product.getId() + " already exists");
        }
        return productRepository.save(product);
    }
    @SneakyThrows
    public Product updateProduct(Product product) {
        getById(product.getId());
        return productRepository.save(product);
    }

    @SneakyThrows
    public void deleteProduct(Long id) {
        getById(id);
        productRepository.deleteById(id);
    }

}
