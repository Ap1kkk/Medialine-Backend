package ru.medialine.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/{id}")
    public Product getNewsById(@PathVariable Long id) {
        log.debug("Get product by id: {}", id);
        return productService.getById(id);
    }
}
