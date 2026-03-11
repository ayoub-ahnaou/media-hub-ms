package com.example.media_service.controller;

import com.example.media_service.dto.MediaRequestDTO;
import com.example.media_service.dto.MediaResponseDTO;
import com.example.media_service.feign.SubscriptionClient;
import com.example.media_service.service.MediaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Media", description = "Gestion du catalogue multimédia (films, séries, podcasts)")
public class MediaController {

    private final MediaService mediaService;
    private final SubscriptionClient subscriptionClient;
    private final WebClient.Builder webClientBuilder;

    @Operation(summary = "Créer un média", description = "Ajoute un nouveau média au catalogue")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Média créé avec succès"),
        @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    public ResponseEntity<MediaResponseDTO> create(@Valid @RequestBody MediaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mediaService.create(dto));
    }

    @Operation(summary = "Obtenir un média par ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Média trouvé"),
        @ApiResponse(responseCode = "404", description = "Média introuvable")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MediaResponseDTO> getById(
            @Parameter(description = "ID du média") @PathVariable Long id) {
        return ResponseEntity.ok(mediaService.getById(id));
    }

    @Operation(summary = "Lister tous les médias")
    @ApiResponse(responseCode = "200", description = "Liste des médias")
    @GetMapping
    public ResponseEntity<List<MediaResponseDTO>> getAll() {
        return ResponseEntity.ok(mediaService.getAll());
    }

    @Operation(summary = "Filtrer par catégorie", description = "Valeurs possibles : FILM, SERIE, PODCAST")
    @ApiResponse(responseCode = "200", description = "Médias filtrés par catégorie")
    @GetMapping("/category/{category}")
    public ResponseEntity<List<MediaResponseDTO>> getByCategory(
            @Parameter(description = "Catégorie (FILM, SERIE, PODCAST)") @PathVariable String category) {
        return ResponseEntity.ok(mediaService.getByCategory(category));
    }

    @Operation(summary = "Filtrer par genre", description = "Valeurs possibles : ACTION, COMEDY, DRAMA, HORROR, DOCUMENTARY, THRILLER")
    @ApiResponse(responseCode = "200", description = "Médias filtrés par genre")
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<MediaResponseDTO>> getByGenre(
            @Parameter(description = "Genre (ACTION, COMEDY, DRAMA...)") @PathVariable String genre) {
        return ResponseEntity.ok(mediaService.getByGenre(genre));
    }

    @Operation(summary = "Rechercher par titre", description = "Recherche insensible à la casse sur le titre")
    @ApiResponse(responseCode = "200", description = "Résultats de la recherche")
    @GetMapping("/search")
    public ResponseEntity<List<MediaResponseDTO>> search(
            @Parameter(description = "Mot-clé à rechercher dans le titre") @RequestParam String keyword) {
        return ResponseEntity.ok(mediaService.search(keyword));
    }

    @Operation(summary = "Modifier un média")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Média mis à jour"),
        @ApiResponse(responseCode = "404", description = "Média introuvable"),
        @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PutMapping("/{id}")
    public ResponseEntity<MediaResponseDTO> update(
            @Parameter(description = "ID du média") @PathVariable Long id,
            @Valid @RequestBody MediaRequestDTO dto) {
        return ResponseEntity.ok(mediaService.update(id, dto));
    }

    @Operation(summary = "Supprimer un média")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Média supprimé"),
        @ApiResponse(responseCode = "404", description = "Média introuvable")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID du média") @PathVariable Long id) {
        mediaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Vérifier l'abonnement (Feign)",
               description = "Appel synchrone via OpenFeign vers subscription-service")
    @ApiResponse(responseCode = "200", description = "Statut d'abonnement retourné")
    @GetMapping("/{id}/check-subscription/{userId}")
    public ResponseEntity<String> checkSubscription(
            @Parameter(description = "ID du média") @PathVariable Long id,
            @Parameter(description = "ID de l'utilisateur") @PathVariable Long userId) {
        String status = subscriptionClient.getUserSubscriptionStatus(userId);
        return ResponseEntity.ok(status);
    }

    @Operation(summary = "Vérifier l'abonnement (WebClient)",
               description = "Appel asynchrone réactif via WebClient vers subscription-service")
    @ApiResponse(responseCode = "200", description = "Statut d'abonnement retourné")
    @GetMapping("/{id}/subscription-status/{userId}")
    public Mono<ResponseEntity<String>> checkSubscriptionReactive(
            @Parameter(description = "ID du média") @PathVariable Long id,
            @Parameter(description = "ID de l'utilisateur") @PathVariable Long userId) {
        return webClientBuilder.build()
            .get()
            .uri("http://subscription-service/api/subscriptions/user/{userId}/status", userId)
            .retrieve()
            .bodyToMono(String.class)
            .map(ResponseEntity::ok);
    }
}
