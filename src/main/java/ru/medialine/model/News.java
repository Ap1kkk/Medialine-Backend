package ru.medialine.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private LocalDate time;

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
