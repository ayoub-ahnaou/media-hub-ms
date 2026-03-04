package com.user_service.user_service.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {
//
//    @Value("${jwt.secret:mySecretKeyForUserServiceJwtTokenGenerationAndValidation12345}")
//    private String secretKey;
//
//    @Value("${jwt.expiration:86400000}")
//    private long jwtExpirationMs;
//
//    /**
//     * Générer un token JWT pour un utilisateur
//     */
//    public String generateToken(String username) {
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
//                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    /**
//     * Extraire le username du token
//     */
//    public String getUsernameFromToken(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
//                .build()
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }
//
//    /**
//     * Valider le token JWT
//     */
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parserBuilder()
//                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
//                    .build()
//                    .parseClaimsJws(token);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    /**
//     * Vérifier si le token a expiré
//     */
//    public boolean isTokenExpired(String token) {
//        try {
//            Date expiration = Jwts.parserBuilder()
//                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
//                    .build()
//                    .parseClaimsJws(token)
//                    .getBody()
//                    .getExpiration();
//            return expiration.before(new Date());
//        } catch (Exception e) {
//            return true;
//        }
//    }
//
//    /**
//     * Générer un refresh token (durée de validité plus longue)
//     */
//    public String generateRefreshToken(String username) {
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + refreshExpirationMs))
//                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
//                .compact();
//    }
}


