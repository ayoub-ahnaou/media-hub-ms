package com.user_service.user_service.controller;

import com.user_service.user_service.dto.LoginRequestDTO;
import com.user_service.user_service.dto.LoginResponseDTO;
import com.user_service.user_service.dto.RefreshTokenRequestDTO;
import com.user_service.user_service.dto.UserRequestDTO;
import com.user_service.user_service.dto.UserResponseDTO;
import com.user_service.user_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

//    @Autowired
//    private AuthService authService;
//
//    /**
//     * Enregistrer un nouvel utilisateur
//     */
//    @PostMapping("/register")
//    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRequestDTO requestDTO) {
//        try {
//            UserResponseDTO user = authService.register(requestDTO);
//            return new ResponseEntity<>(user, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    /**
//     * Authentifier un utilisateur et retourner les tokens
//     */
//    @PostMapping("/login")
//    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO requestDTO) {
//        try {
//            LoginResponseDTO response = authService.login(requestDTO);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.UNAUTHORIZED);
//        }
//    }
//
//    /**
//     * Rafraîchir le token JWT
//     */
//    @PostMapping("/refresh-token")
//    public ResponseEntity<LoginResponseDTO> refreshToken(@RequestBody RefreshTokenRequestDTO requestDTO) {
//        try {
//            LoginResponseDTO response = authService.refreshToken(requestDTO.getRefreshToken());
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.UNAUTHORIZED);
//        }
//    }
//
//    /**
//     * Valider un token JWT
//     */
//    @GetMapping("/validate")
//    public ResponseEntity<Boolean> validateToken(@RequestParam String token) {
//        try {
//            boolean isValid = authService.validateToken(token);
//            return new ResponseEntity<>(isValid, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(false, HttpStatus.OK);
//        }
//    }
//
//    /**
//     * Extraire le username du token
//     */
//    @GetMapping("/username")
//    public ResponseEntity<String> getUsernameFromToken(@RequestParam String token) {
//        try {
//            String username = authService.getUsernameFromToken(token);
//            return new ResponseEntity<>(username, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.UNAUTHORIZED);
//        }
//    }
}

