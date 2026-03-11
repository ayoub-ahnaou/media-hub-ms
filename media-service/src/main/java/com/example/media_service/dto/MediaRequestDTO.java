package com.example.media_service.dto;

import com.example.media_service.enums.Category;
import com.example.media_service.enums.Genre;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Données requises pour créer ou modifier un média")
public class MediaRequestDTO {

    @Schema(description = "Titre du média", example = "Inception", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Title is required")
    private String title;

    @Schema(description = "Description du média", example = "Un voleur qui s'infiltre dans les rêves...")
    private String description;

    @Schema(description = "Catégorie du média", example = "FILM", allowableValues = {"FILM", "SERIE", "PODCAST"}, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private Category category;

    @Schema(description = "Genre du média", example = "ACTION", allowableValues = {"ACTION", "COMEDY", "DRAMA", "HORROR", "DOCUMENTARY", "THRILLER"}, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private Genre genre;

    @Schema(description = "Réalisateur", example = "Christopher Nolan")
    private String director;

    @Schema(description = "Année de sortie", example = "2010")
    private Integer releaseYear;

    @Schema(description = "URL de la miniature", example = "https://cdn.mediahub.com/inception.jpg")
    private String thumbnailUrl;

    @Schema(description = "URL de streaming", example = "https://stream.mediahub.com/inception.mp4")
    private String streamUrl;
}
