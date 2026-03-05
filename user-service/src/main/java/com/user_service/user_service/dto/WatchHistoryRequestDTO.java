package com.user_service.user_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WatchHistoryRequestDTO {

    @NotNull(message = "Le mediaId est obligatoire")
    private Long mediaId;

    @Min(value = 0, message = "Le progress ne peut pas être négatif")
    private Integer progress; // en secondes
}