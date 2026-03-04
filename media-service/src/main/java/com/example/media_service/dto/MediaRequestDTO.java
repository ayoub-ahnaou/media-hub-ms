package com.example.media_service.dto;

import com.example.media_service.enums.Category;
import com.example.media_service.enums.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MediaRequestDTO {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotNull
    private Category category;

    @NotNull
    private Genre genre;

    private String director;
    private Integer releaseYear;
    private String thumbnailUrl;
    private String streamUrl;
}
