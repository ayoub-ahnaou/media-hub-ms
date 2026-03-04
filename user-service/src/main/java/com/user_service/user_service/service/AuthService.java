package com.user_service.user_service.service;

import com.user_service.user_service.dto.LoginRequestDTO;
import com.user_service.user_service.dto.LoginResponseDTO;
import com.user_service.user_service.dto.UserRequestDTO;
import com.user_service.user_service.dto.UserResponseDTO;
import com.user_service.user_service.model.User;
import com.user_service.user_service.repository.UserRepository;
import com.user_service.user_service.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private JwtService jwtService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    /**
//     * Enregistrer un nouvel utilisateur
//     */
//    public UserResponseDTO register(UserRequestDTO requestDTO) {
//        // Vérifier si l'utilisateur existe déjà
//        if (userRepository.existsByUsername(requestDTO.getUsername())) {
//            throw new RuntimeException("Username already exists");
//        }
//
//        if (userRepository.existsByEmail(requestDTO.getEmail())) {
//            throw new RuntimeException("Email already exists");
//        }
//
//        // Créer le nouvel utilisateur
//        User user = new User();
//        user.setUsername(requestDTO.getUsername());
//        user.setEmail(requestDTO.getEmail());
//        user.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
////        user.setFirstName(requestDTO.getFirstName());
////        user.setLastName(requestDTO.getLastName());
////        user.setPhoneNumber(requestDTO.getPhoneNumber());
////        user.setIsActive(true);
//
//        User savedUser = userRepository.save(user);
//        return convertToResponseDTO(savedUser);
//    }
//
//    /**
//     * Authentifier un utilisateur et retourner les tokens
//     */
//    public LoginResponseDTO login(LoginRequestDTO requestDTO) {
//        User user = userRepository.findByUsername(requestDTO.getUsername())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        if (!passwordEncoder.matches(requestDTO.getPassword(), user.getPassword())) {
//            throw new RuntimeException("Invalid password");
//        }
//
////        if (!user.getIsActive()) {
////            throw new RuntimeException("User account is inactive");
////        }
//
////        String token = jwtService.generateToken(user.getUsername());
////        String refreshToken = jwtService.generateRefreshToken(user.getUsername());
//
//        LoginResponseDTO response = new LoginResponseDTO();
//        response.setToken(token);
//        response.setRefreshToken(refreshToken);
//        response.setUsername(user.getUsername());
//        response.setExpiresIn(86400000L); // 24 heures en millisecondes
//
//        return response;
//    }
//
//    /**
//     * Rafraîchir le token JWT
//     */
//    public LoginResponseDTO refreshToken(String refreshToken) {
//        if (!jwtService.validateToken(refreshToken)) {
//            throw new RuntimeException("Invalid refresh token");
//        }
//
//        String username = jwtService.getUsernameFromToken(refreshToken);
//        String newToken = jwtService.generateToken(username);
//        String newRefreshToken = jwtService.generateRefreshToken(username);
//
//        LoginResponseDTO response = new LoginResponseDTO();
//        response.setToken(newToken);
//        response.setRefreshToken(newRefreshToken);
//        response.setUsername(username);
//        response.setExpiresIn(86400000L);
//
//        return response;
//    }
//
//    /**
//     * Valider un token JWT
//     */
//    public boolean validateToken(String token) {
//        return jwtService.validateToken(token);
//    }
//
//    /**
//     * Extraire le username du token
//     */
//    public String getUsernameFromToken(String token) {
//        return jwtService.getUsernameFromToken(token);
//    }
//
//    /**
//     * Convertir User à UserResponseDTO
//     */
//    private UserResponseDTO convertToResponseDTO(User user) {
//        UserResponseDTO dto = new UserResponseDTO();
//        dto.setId(user.getId());
//        dto.setUsername(user.getUsername());
//        dto.setEmail(user.getEmail());
//        dto.setFirstName(user.getFirstName());
//        dto.setLastName(user.getLastName());
//        dto.setPhoneNumber(user.getPhoneNumber());
//        dto.setIsActive(user.getIsActive());
//        dto.setCreatedAt(user.getCreatedAt());
//        dto.setUpdatedAt(user.getUpdatedAt());
//        return dto;
//    }
}

