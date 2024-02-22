package ru.medialine.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.medialine.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.category = (SELECT c FROM Category c WHERE c.id = 1) " +
            "WHERE p.category = (SELECT c FROM Category c WHERE c.id = :categoryId)")
    void updateProductsCategory(@Param("categoryId") Long categoryId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Subcategory s WHERE s.category = (SELECT c FROM Category c WHERE c.id = :categoryId)")
    void deleteSubcategoriesByCategory(@Param("categoryId") Long categoryId);
}
