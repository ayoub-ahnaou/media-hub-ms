package com.example.media_service.controller;

import com.example.media_service.dto.MediaRequestDTO;
import com.example.media_service.dto.MediaResponseDTO;
import com.example.media_service.feign.SubscriptionClient;
import com.example.media_service.service.MediaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/medias")
@RequiredArgsConstructor
@Validated
public class MediaController {

    private final MediaService mediaService;
    private final SubscriptionClient subscriptionClient;
    private final WebClient.Builder webClientBuilder;

    @PostMapping
    public ResponseEntity<MediaResponseDTO> create(@Valid @RequestBody MediaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mediaService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MediaResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(mediaService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<MediaResponseDTO>> getAll() {
        return ResponseEntity.ok(mediaService.getAll());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<MediaResponseDTO>> getByCategory(@PathVariable String category) {
        return ResponseEntity.ok(mediaService.getByCategory(category));
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<MediaResponseDTO>> getByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(mediaService.getByGenre(genre));
    }

    @GetMapping("/search")
    public ResponseEntity<List<MediaResponseDTO>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(mediaService.search(keyword));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MediaResponseDTO> update(@PathVariable Long id,
                                                    @Valid @RequestBody MediaRequestDTO dto) {
        return ResponseEntity.ok(mediaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        mediaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Feign: check subscription status (synchronous)
    @GetMapping("/{id}/check-subscription/{userId}")
    public ResponseEntity<String> checkSubscription(@PathVariable Long id, @PathVariable Long userId) {
        String status = subscriptionClient.getUserSubscriptionStatus(userId);
        return ResponseEntity.ok(status);
    }

    // WebClient: async subscription check (reactive)
    @GetMapping("/{id}/subscription-status/{userId}")
    public Mono<ResponseEntity<String>> checkSubscriptionReactive(@PathVariable Long id, @PathVariable Long userId) {
        return webClientBuilder.build()
            .get()
            .uri("http://subscription-service/api/subscriptions/user/{userId}/status", userId)
            .retrieve()
            .bodyToMono(String.class)
            .map(ResponseEntity::ok);
    }
}
