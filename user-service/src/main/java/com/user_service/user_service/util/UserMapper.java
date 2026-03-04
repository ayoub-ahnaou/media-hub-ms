package com.user_service.user_service.util;

import com.user_service.user_service.dto.UserRequestDTO;
import com.user_service.user_service.dto.UserResponseDTO;
import com.user_service.user_service.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
//
//    /**
//     * Convertir UserRequestDTO en entité User
//     */
//    public User toEntity(UserRequestDTO requestDTO) {
//        if (requestDTO == null) {
//            return null;
//        }
//
//        User user = new User();
//        user.setUsername(requestDTO.getUsername());
//        user.setEmail(requestDTO.getEmail());
//        user.setPassword(requestDTO.getPassword());
//        user.setFirstName(requestDTO.getFirstName());
//        user.setLastName(requestDTO.getLastName());
//        user.setPhoneNumber(requestDTO.getPhoneNumber());
//        return user;
//    }
//
//    /**
//     * Convertir entité User en UserResponseDTO
//     */
//    public UserResponseDTO toResponseDTO(User user) {
//        if (user == null) {
//            return null;
//        }
//
//        UserResponseDTO responseDTO = new UserResponseDTO();
//        responseDTO.setId(user.getId());
//        responseDTO.setUsername(user.getUsername());
//        responseDTO.setEmail(user.getEmail());
//        responseDTO.setFirstName(user.getFirstName());
//        responseDTO.setLastName(user.getLastName());
//        responseDTO.setPhoneNumber(user.getPhoneNumber());
//        responseDTO.setIsActive(user.getIsActive());
//        responseDTO.setCreatedAt(user.getCreatedAt());
//        responseDTO.setUpdatedAt(user.getUpdatedAt());
//        return responseDTO;
//    }
//
//    /**
//     * Mettre à jour une entité User avec les données du DTO
//     */
//    public void updateEntityFromDTO(UserRequestDTO requestDTO, User user) {
//        if (requestDTO == null || user == null) {
//            return;
//        }
//
//        if (requestDTO.getUsername() != null) {
//            user.setUsername(requestDTO.getUsername());
//        }
//        if (requestDTO.getEmail() != null) {
//            user.setEmail(requestDTO.getEmail());
//        }
//        if (requestDTO.getPassword() != null) {
//            user.setPassword(requestDTO.getPassword());
//        }
//        if (requestDTO.getFirstName() != null) {
//            user.setFirstName(requestDTO.getFirstName());
//        }
//        if (requestDTO.getLastName() != null) {
//            user.setLastName(requestDTO.getLastName());
//        }
//        if (requestDTO.getPhoneNumber() != null) {
//            user.setPhoneNumber(requestDTO.getPhoneNumber());
//        }
//    }
}

