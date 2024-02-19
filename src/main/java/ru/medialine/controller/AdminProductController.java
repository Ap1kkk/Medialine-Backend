package ru.medialine.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.medialine.model.Product;
import ru.medialine.service.FileService;
import ru.medialine.service.ProductService;

@Slf4j
@RestController
@RequestMapping("/api/admin/products")
@RequiredArgsConstructor
public class AdminProductController {

    private final ProductService productService;
    private final FileService fileService;

    private static final String fileParamName = "image";

    @PostMapping()
    public Product addProduct(@ModelAttribute Product product, @RequestPart(fileParamName) MultipartFile file) {
        log.debug("Add product: {}", product.toString());
        return productService.addProduct(product, file);
    }
    @PatchMapping()
    public Product updateProduct(@ModelAttribute Product product, @RequestPart(fileParamName) MultipartFile file) {
        log.debug("Update product: {}", product.toString());
        return productService.updateProduct(product, file);
    }

    @DeleteMapping()
    public void deleteProduct(@RequestParam Long id) {
        log.debug("Delete product with id: {}", id);
        productService.deleteProduct(id);
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("image") MultipartFile file) {
        return fileService.upload(file);
    }
}
