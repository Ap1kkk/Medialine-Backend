package ru.medialine.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String description;

    @ElementCollection
    @Column(name = "specials", length = 255)
    @CollectionTable(name = "medialine_products_specials", joinColumns = @JoinColumn(name = "product_id"))
    private List<String> specials;

    @ElementCollection
    @Column(name = "packaging", length = 255)
    @CollectionTable(name = "medialine_products_packaging", joinColumns = @JoinColumn(name = "product_id"))
    private List<String> packaging;

    @Column(name = "image_path",nullable = false)
    private String imagePath;
}
