package ru.medialine.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.medialine.model.News;
import ru.medialine.model.Product;
import ru.medialine.service.ProductService;

import java.util.List;

@Slf4j
@RestController
@Controller
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        log.debug("Get all products");
        return productService.getAllProducts();
    }

    @PostMapping(value = "/add")
    public Product addProduct(@RequestBody Product product) {
        log.debug("Add product: {}", product.toString());
        return productService.addProduct(product);
    }
    @PatchMapping("/update")
    public Product updateProduct(@RequestBody Product product) {
        log.debug("Update product: {}", product.toString());
        return productService.updateProduct(product);
    }

    @DeleteMapping("/delete")
    public void deleteProduct(@RequestParam Long id) {
        log.debug("Delete product with id: {}", id);
        productService.deleteProduct(id);
    }
}
