package ru.medialine.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.medialine.dto.ProductDto;
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

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Product addProduct(ProductDto productDto) {
        log.debug("Add product: {}", productDto.toString());
        return productService.addProduct(productDto);
    }

    @PatchMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Product updateProduct(ProductDto productDto) {
        log.debug("Update product: {}", productDto.toString());
        return productService.updateProduct(productDto);
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
