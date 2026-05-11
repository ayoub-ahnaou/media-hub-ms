package com.user_service.user_service.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "watch_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WatchHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "media_id", nullable = false)
    private Long mediaId; // référence vers media-service (pas de FK réelle)

    @CreationTimestamp
    @Column(name = "watched_at", updatable = false)
    private LocalDateTime watchedAt;

    @Column(name = "progress")
    private Integer progress; // durée visionnée en secondes
}