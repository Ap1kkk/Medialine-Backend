package ru.medialine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.medialine.model.Category;
import ru.medialine.model.Subcategory;

import java.util.List;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
    List<Subcategory> findAllByCategoryId(Long id);
    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.subcategory = null WHERE p.subcategory.id = :deletedSubcategoryId")
    void updateProductSubcategoryToNull(@Param("deletedSubcategoryId") Long deletedSubcategoryId);

}
