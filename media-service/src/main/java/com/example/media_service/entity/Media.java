package com.example.media_service.entity;

import com.example.media_service.enums.Category;
import com.example.media_service.enums.Genre;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "medias")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private String director;
    private Integer releaseYear;
    private String thumbnailUrl;
    private String streamUrl;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
