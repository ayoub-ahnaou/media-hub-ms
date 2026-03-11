package com.example.media_service.dto;

import com.example.media_service.enums.Category;
import com.example.media_service.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MediaResponseDTO {

    private Long id;
    private String title;
    private String description;
    private Category category;
    private Genre genre;
    private String director;
    private Integer releaseYear;
    private String thumbnailUrl;
    private String streamUrl;
    private LocalDateTime createdAt;
}
