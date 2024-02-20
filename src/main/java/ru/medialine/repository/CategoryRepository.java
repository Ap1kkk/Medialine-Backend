package ru.medialine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.medialine.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
