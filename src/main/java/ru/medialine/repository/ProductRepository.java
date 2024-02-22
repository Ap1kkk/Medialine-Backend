package ru.medialine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.medialine.model.Category;
import ru.medialine.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategory(Category category);
    List<Product> findAllBySubcategoryId(Long id);
}
