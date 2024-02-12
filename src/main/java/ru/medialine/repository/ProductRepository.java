package ru.medialine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.medialine.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
