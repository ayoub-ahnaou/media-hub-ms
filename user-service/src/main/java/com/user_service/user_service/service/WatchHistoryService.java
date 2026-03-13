package com.user_service.user_service.service;

import com.user_service.user_service.dto.WatchHistoryRequestDTO;
import com.user_service.user_service.dto.WatchHistoryResponseDTO;
import com.user_service.user_service.exception.UserNotFoundException;
import com.user_service.user_service.exception.WatchHistoryNotFoundException;
import com.user_service.user_service.mapper.WatchHistoryMapper;
import com.user_service.user_service.model.User;
import com.user_service.user_service.model.WatchHistory;
import com.user_service.user_service.repository.UserRepository;
import com.user_service.user_service.repository.WatchHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WatchHistoryService {
    private final WatchHistoryRepository watchHistoryRepository;
    private final UserRepository userRepository;
    private final WatchHistoryMapper watchHistoryMapper;

    /**
     * ✅ Ajouter OU mettre à jour le progress d'un média visionné.
     * Si le user a déjà visionné ce média → on met à jour le progress.
     * Sinon → on crée une nouvelle entrée.
     */
    
    @Transactional
    public WatchHistoryResponseDTO addOrUpdateHistory(Long userId, WatchHistoryRequestDTO dto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Utilisateur introuvable : " + userId));

        Optional<WatchHistory> existing =
                watchHistoryRepository.findByUserIdAndMediaId(userId, dto.getMediaId());

        WatchHistory watchHistory;

        if (existing.isPresent()) {
            // ── Mise à jour du progress
            watchHistory = existing.get();
            watchHistory.setProgress(dto.getProgress());
        } else {
            // ── Nouvelle entrée
            watchHistory = watchHistoryMapper.toEntity(dto);
            watchHistory.setUser(user);
        }

        return watchHistoryMapper.toDTO(watchHistoryRepository.save(watchHistory));
    }

    /**
     * ✅ Récupérer tout l'historique d'un user (trié du plus récent au plus ancien).
     */
    
    public List<WatchHistoryResponseDTO> getHistoryByUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("Utilisateur introuvable : " + userId);
        }
        return watchHistoryRepository.findByUserIdOrderByWatchedAtDesc(userId)
                .stream()
                .map(watchHistoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * ✅ Supprimer une entrée précise de l'historique.
     */
    
    @Transactional
    public void deleteHistory(Long userId, Long historyId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("Utilisateur introuvable : " + userId);
        }
        WatchHistory wh = watchHistoryRepository.findById(historyId)
                .orElseThrow(() -> new WatchHistoryNotFoundException("Historique introuvable : " + historyId));

        // Sécurité : vérifier que l'entrée appartient bien à ce user
        if (!wh.getUser().getId().equals(userId)) {
            throw new WatchHistoryNotFoundException("Cet historique n'appartient pas à l'utilisateur : " + userId);
        }
        watchHistoryRepository.deleteById(historyId);
    }

    /**
     * ✅ Supprimer tout l'historique d'un user.
     */
    
    @Transactional
    public void deleteAllHistory(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("Utilisateur introuvable : " + userId);
        }
        watchHistoryRepository.deleteByUserId(userId);
    }
}
