package com.user_service.user_service.controller;

import com.user_service.user_service.dto.WatchHistoryRequestDTO;
import com.user_service.user_service.dto.WatchHistoryResponseDTO;

import com.user_service.user_service.service.WatchHistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/history")
@RequiredArgsConstructor
public class WatchHistoryController {

    private final WatchHistoryService watchHistoryService;

    // ✅ POST /api/users/{userId}/history
    // Ajouter ou mettre à jour un média visionné
    @PostMapping
    public ResponseEntity<WatchHistoryResponseDTO> addOrUpdateHistory(
            @PathVariable Long userId,
            @Valid @RequestBody WatchHistoryRequestDTO dto) {

        WatchHistoryResponseDTO result = watchHistoryService.addOrUpdateHistory(userId, dto);
        return ResponseEntity.ok(result);
    }

    // ✅ GET /api/users/{userId}/history
    // Récupérer tout l'historique du user
    @GetMapping
    public ResponseEntity<List<WatchHistoryResponseDTO>> getHistory(
            @PathVariable Long userId) {

        return ResponseEntity.ok(watchHistoryService.getHistoryByUser(userId));

    }

    // ✅ DELETE /api/users/{userId}/history/{historyId}
    // Supprimer une entrée précise
    @DeleteMapping("/{historyId}")
    public ResponseEntity<String> deleteOne(
            @PathVariable Long userId,
            @PathVariable Long historyId) {

        watchHistoryService.deleteHistory(userId, historyId);
        return ResponseEntity.ok("Entrée supprimée");

    }

    // ✅ DELETE /api/users/{userId}/history
    // Vider tout l'historique du user
    @DeleteMapping
    public ResponseEntity<String> deleteAll(@PathVariable Long userId) {
        watchHistoryService.deleteAllHistory(userId);
        return ResponseEntity.ok("Historique vidé");

    }
}
