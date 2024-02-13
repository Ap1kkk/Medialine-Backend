package ru.medialine.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.medialine.model.Product;
import ru.medialine.service.ProductService;

@Slf4j
@RestController
@RequestMapping("/api/admin/products")
@RequiredArgsConstructor
public class AdminProductController {

    private final ProductService productService;

    @PostMapping()
    public Product addProduct(@RequestBody Product product) {
        log.debug("Add product: {}", product.toString());
        return productService.addProduct(product);
    }
    @PatchMapping()
    public Product updateProduct(@RequestBody Product product) {
        log.debug("Update product: {}", product.toString());
        return productService.updateProduct(product);
    }

    @DeleteMapping()
    public void deleteProduct(@RequestParam Long id) {
        log.debug("Delete product with id: {}", id);
        productService.deleteProduct(id);
    }
}
