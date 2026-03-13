package com.user_service.user_service.repository;

import com.user_service.user_service.model.WatchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WatchHistoryRepository extends JpaRepository<WatchHistory, Long> {

    // Tout l'historique d'un user
    List<WatchHistory> findByUserIdOrderByWatchedAtDesc(Long userId);

    // Vérifier si un user a déjà visionné un média
    Optional<WatchHistory> findByUserIdAndMediaId(Long userId, Long mediaId);

    // Supprimer tout l'historique d'un user
    void deleteByUserId(Long userId);
}
