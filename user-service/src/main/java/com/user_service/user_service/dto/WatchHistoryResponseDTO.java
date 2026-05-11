package com.user_service.user_service.dto;

import java.time.LocalDateTime;

public class WatchHistoryResponseDTO {
    private Long          id;
    private Long          userId;
    private String        username;
    private Long          mediaId;
    private Integer       progress;    // durée visionnée en secondes
    private LocalDateTime watchedAt;
}
