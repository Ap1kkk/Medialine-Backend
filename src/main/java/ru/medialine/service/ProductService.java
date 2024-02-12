package ru.medialine.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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
    public Product addProduct(Product product) {
        if(product.getId() != null) {
            if(productRepository.findById(product.getId()).isPresent())
                throw new Exception("Product with id " + product.getId() + " already exists");
        }
        return productRepository.save(product);
    }
    @SneakyThrows
    public Product updateProduct(Product product) {
        findById(product.getId());
        return productRepository.save(product);
    }
    public void deleteProduct(Long id) {
        findById(id);
        productRepository.deleteById(id);
    }

    @SneakyThrows
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new Exception("Unable to find product by id " + id));
    }
}
