package com.example.media_service.dto;

import com.example.media_service.enums.Category;
import com.example.media_service.enums.Genre;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MediaResponseDTO {

    private Long id;
    private String title;
    private String description;
    private Category category;
    private Genre genre;
    private String director;
    private Integer releaseYear;
    private String thumbnailUrl;
    private LocalDateTime createdAt;
}
