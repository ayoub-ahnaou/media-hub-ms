# 🎬 Media Service — MEDIAHUB Platform

Microservice de gestion du **catalogue multimédia** (films, séries, podcasts) pour la plateforme **MEDIAHUB Streaming Backend**.

---

## 📋 Table des matières

- [Architecture](#architecture)
- [Technologies](#technologies)
- [Prérequis](#prérequis)
- [Lancement](#lancement)
- [API Endpoints](#api-endpoints)
- [Swagger UI](#swagger-ui)
- [Configuration](#configuration)
- [Communication inter-services](#communication-inter-services)
- [Structure du projet](#structure-du-projet)

---

## Architecture

Ce service fait partie d'une architecture microservices :

```
MEDIAHUB
├── discovery-server    (Eureka - port 8761)
├── config-server       (Spring Cloud Config - port 8888)
├── user-service        (port 8081)
├── media-service       (port 8082)  ← CE SERVICE
└── subscription-service (port 8083)
```

---

## Technologies

| Technologie            | Version  | Rôle                              |
|------------------------|----------|-----------------------------------|
| Java                   | 17       | Langage                           |
| Spring Boot            | 4.0.3    | Framework principal               |
| Spring Data JPA        | -        | Accès base de données             |
| Spring Cloud Eureka    | 2025.1.0 | Découverte de services            |
| Spring Cloud OpenFeign | 2025.1.0 | Communication inter-services sync |
| Spring WebFlux/WebClient| -       | Communication inter-services async|
| Spring Cloud Config    | 2025.1.0 | Configuration centralisée         |
| MySQL                  | 8.0      | Base de données                   |
| SpringDoc OpenAPI      | 2.8.3    | Documentation Swagger             |
| Lombok                 | -        | Réduction du boilerplate          |
| Docker                 | -        | Conteneurisation                  |

---

## Prérequis

- Java 17+
- Docker & Docker Compose
- Gradle 8+

---

## Lancement

### Avec Docker Compose (recommandé)

```bash
# Depuis le dossier media-service
docker-compose up -d
```

Cela démarre :
- **MySQL 8.0** sur le port `3306`
- **media-service** sur le port `8082`

### En local (dev)

```bash
# Build
./gradlew build

# Lancer
./gradlew bootRun
```

> Assurez-vous que MySQL tourne localement et que les variables de `application.properties` correspondent.

### Build du JAR pour Docker

```bash
./gradlew build -x test
docker build -t media-service:latest .
```

---

## API Endpoints

| Méthode  | Endpoint                                       | Description                              |
|----------|------------------------------------------------|------------------------------------------|
| `POST`   | `/api/medias`                                  | Créer un média                           |
| `GET`    | `/api/medias`                                  | Lister tous les médias                   |
| `GET`    | `/api/medias/{id}`                             | Obtenir un média par ID                  |
| `PUT`    | `/api/medias/{id}`                             | Modifier un média                        |
| `DELETE` | `/api/medias/{id}`                             | Supprimer un média                       |
| `GET`    | `/api/medias/category/{category}`              | Filtrer par catégorie                    |
| `GET`    | `/api/medias/genre/{genre}`                    | Filtrer par genre                        |
| `GET`    | `/api/medias/search?keyword=`                  | Rechercher par titre                     |
| `GET`    | `/api/medias/{id}/check-subscription/{userId}` | Vérifier abonnement (Feign - sync)       |
| `GET`    | `/api/medias/{id}/subscription-status/{userId}`| Vérifier abonnement (WebClient - async)  |

### Valeurs acceptées

**Category** : `FILM` · `SERIE` · `PODCAST`

**Genre** : `ACTION` · `COMEDY` · `DRAMA` · `HORROR` · `DOCUMENTARY` · `THRILLER`

### Exemple de requête POST

```json
POST /api/medias
Content-Type: application/json

{
  "title": "Inception",
  "description": "Un voleur qui s'infiltre dans les rêves...",
  "category": "FILM",
  "genre": "ACTION",
  "director": "Christopher Nolan",
  "releaseYear": 2010,
  "thumbnailUrl": "https://cdn.mediahub.com/inception.jpg",
  "streamUrl": "https://stream.mediahub.com/inception.mp4"
}
```

### Exemple de réponse

```json
{
  "id": 1,
  "title": "Inception",
  "description": "Un voleur qui s'infiltre dans les rêves...",
  "category": "FILM",
  "genre": "ACTION",
  "director": "Christopher Nolan",
  "releaseYear": 2010,
  "thumbnailUrl": "https://cdn.mediahub.com/inception.jpg",
  "streamUrl": "https://stream.mediahub.com/inception.mp4",
  "createdAt": "2026-03-04T10:00:00"
}
```

---

## Swagger UI

Une fois le service lancé, accédez à la documentation interactive :

| URL | Description |
|-----|-------------|
| `http://localhost:8082/swagger-ui.html` | Interface Swagger UI |
| `http://localhost:8082/v3/api-docs`     | Spec OpenAPI JSON   |

---

## Configuration

### `application.properties`

```properties
# Datasource
spring.datasource.url=jdbc:mysql://localhost:3306/media_db
spring.datasource.username=media_user
spring.datasource.password=media_password

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Swagger
springdoc.swagger-ui.path=/swagger-ui.html
```

### `bootstrap.yml`

```yaml
spring:
  application:
    name: media-service
  config:
    import: optional:configserver:http://config-server:8888
  profiles:
    active: dev

server:
  port: 8082

eureka:
  client:
    service-url:
      defaultZone: http://discovery-server:8761/eureka/
```

---

## Communication inter-services

### OpenFeign (synchrone)

```java
@FeignClient(name = "subscription-service")
public interface SubscriptionClient {
    @GetMapping("/api/subscriptions/user/{userId}/status")
    String getUserSubscriptionStatus(@PathVariable Long userId);
}
```

- Le nom `"subscription-service"` est résolu automatiquement par **Eureka**.
- Aucune URL codée en dur.

### WebClient (asynchrone / réactif)

```java
webClientBuilder.build()
    .get()
    .uri("http://subscription-service/api/subscriptions/user/{userId}/status", userId)
    .retrieve()
    .bodyToMono(String.class);
```

- `WebClient.Builder` est annoté `@LoadBalanced` pour la résolution via Eureka.

---

## Structure du projet

```
media-service/
├── src/main/java/com/example/media_service/
│   ├── MediaServiceApplication.java     # Point d'entrée + @EnableFeignClients
│   ├── config/
│   │   ├── WebClientConfig.java         # WebClient.Builder @LoadBalanced
│   │   └── OpenApiConfig.java           # Configuration Swagger/OpenAPI
│   ├── controller/
│   │   └── MediaController.java         # REST endpoints
│   ├── dto/
│   │   ├── MediaRequestDTO.java         # Payload entrant (validé)
│   │   └── MediaResponseDTO.java        # Payload sortant
│   ├── entity/
│   │   └── Media.java                   # Entité JPA
│   ├── enums/
│   │   ├── Category.java                # FILM, SERIE, PODCAST
│   │   └── Genre.java                   # ACTION, COMEDY, DRAMA...
│   ├── exception/
│   │   ├── GlobalExceptionHandler.java  # @RestControllerAdvice
│   │   ├── MediaNotFoundException.java  # Exception métier
│   │   └── ApiErrorResponse.java        # Réponse d'erreur standardisée
│   ├── feign/
│   │   └── SubscriptionClient.java      # Client Feign → subscription-service
│   ├── repository/
│   │   └── MediaRepository.java         # JPA Repository + requêtes custom
│   └── service/
│       ├── MediaService.java            # Interface
│       └── MediaServiceImpl.java        # Implémentation
├── src/main/resources/
│   ├── application.properties           # Config datasource + Swagger
│   └── bootstrap.yml                    # Config Eureka + Config Server
├── build.gradle                         # Dépendances Gradle
├── Dockerfile                           # Image Docker
└── docker-compose.yml                   # MySQL + media-service
```

---

## Gestion des erreurs

Toutes les erreurs retournent un format standardisé :

```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Media not found: 99",
  "timestamp": "2026-03-04T10:00:00"
}
```

| Code HTTP | Cas                        |
|-----------|----------------------------|
| `400`     | Validation DTO échouée     |
| `400`     | Valeur enum invalide       |
| `404`     | Média introuvable          |
| `500`     | Erreur interne inattendue  |
