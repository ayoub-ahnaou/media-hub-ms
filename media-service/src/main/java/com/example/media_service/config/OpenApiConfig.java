package com.example.media_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI mediaServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Media Service API")
                        .description("""
                                API REST du Media Service — MEDIAHUB Streaming Backend Platform.
                                
                                Ce service gère le catalogue multimédia (films, séries, podcasts) :
                                - CRUD complet des médias
                                - Recherche par catégorie et genre
                                - Vérification d'abonnement via Feign & WebClient
                                """)
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("MEDIAHUB Team")
                                .email("contact@mediahub.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .servers(List.of(
                        new Server().url("http://localhost:8082").description("Local Development"),
                        new Server().url("http://media-service:8082").description("Docker / Eureka")
                ));
    }
}
