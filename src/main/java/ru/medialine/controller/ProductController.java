package ru.medialine.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        try {
            log.debug("Get all products");
            return productService.getAllProducts();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        try {
            log.debug("Add product: {}", product.toString());
            return new ResponseEntity<>(productService.addProduct(product), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
    @PatchMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        try {
            log.debug("Update product: {}", product.toString());
            return new ResponseEntity<>(productService.updateProduct(product), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/delete")
    public void deleteProduct(@RequestParam Long id) {
        try {
            log.debug("Delete product with id: {}", id);
            productService.deleteProduct(id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
