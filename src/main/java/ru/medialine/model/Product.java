package ru.medialine.model;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.lang.reflect.Array;
import java.util.List;

@Entity
@Table(name = "medialine_products")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "subcategory_id", referencedColumnName = "id")
    private Subcategory subcategory;

    @Column(nullable = false)
    private String description;

    @Column(name = "specials", columnDefinition = "TEXT[]")
    private String specials;

    @Column(name = "packaging", columnDefinition = "TEXT[]")
    private String packaging;

    @Column(name = "image_path",nullable = false)
    private String imagePath;
}
