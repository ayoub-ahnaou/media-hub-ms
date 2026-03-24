package com.user_service.user_service.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WatchHistoryResponseDTO {
    private Long          id;
    private Long          userId;
    private String        username;
    private Long          mediaId;
    private Integer       progress;    // durée visionnée en secondes
    private LocalDateTime watchedAt;
}
