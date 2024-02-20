package ru.medialine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.medialine.model.Subcategory;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
}
