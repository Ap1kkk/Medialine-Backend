package ru.medialine.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "medialine_news")
@Data
@NoArgsConstructor
public class News {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Date time;

    @Column(nullable = false)
    private String text;

    @Column(name = "image_path",nullable = false)
    private String imagePath;

    public News(String title, String text, String imagePath) {
        this.title = title;
        this.text = text;
        this.imagePath = imagePath;
    }
}
