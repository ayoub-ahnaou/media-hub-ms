package com.user_service.user_service.service;

import com.user_service.user_service.dto.UserRequestDTO;
import com.user_service.user_service.dto.UserResponseDTO;
import com.user_service.user_service.model.User;
import com.user_service.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    // Create a new user
//    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
//        User user = new User();
//        user.setUsername(userRequestDTO.getUsername());
//        user.setEmail(userRequestDTO.getEmail());
//        user.setPassword(userRequestDTO.getPassword());
//        user.setFirstName(userRequestDTO.getFirstName());
//        user.setLastName(userRequestDTO.getLastName());
//        user.setPhoneNumber(userRequestDTO.getPhoneNumber());
//        user.setIsActive(true);
//
//        User savedUser = userRepository.save(user);
//        return convertToResponseDTO(savedUser);
//    }
//
//    // Get user by ID
//    public UserResponseDTO getUserById(Long id) {
//        return userRepository.findById(id)
//                .map(this::convertToResponseDTO)
//                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
//    }
//
//    // Get user by username
//    public UserResponseDTO getUserByUsername(String username) {
//        return userRepository.findByUsername(username)
//                .map(this::convertToResponseDTO)
//                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
//    }
//
//    // Get user by email
//    public UserResponseDTO getUserByEmail(String email) {
//        return userRepository.findByEmail(email)
//                .map(this::convertToResponseDTO)
//                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
//    }
//
//    // Get all users
//    public List<UserResponseDTO> getAllUsers() {
//        return userRepository.findAll().stream()
//                .map(this::convertToResponseDTO)
//                .collect(Collectors.toList());
//    }
//
//    // Update user
//    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
//
//        if (userRequestDTO.getUsername() != null) {
//            user.setUsername(userRequestDTO.getUsername());
//        }
//        if (userRequestDTO.getEmail() != null) {
//            user.setEmail(userRequestDTO.getEmail());
//        }
//        if (userRequestDTO.getPassword() != null) {
//            user.setPassword(userRequestDTO.getPassword());
//        }
//        if (userRequestDTO.getFirstName() != null) {
//            user.setFirstName(userRequestDTO.getFirstName());
//        }
//        if (userRequestDTO.getLastName() != null) {
//            user.setLastName(userRequestDTO.getLastName());
//        }
//        if (userRequestDTO.getPhoneNumber() != null) {
//            user.setPhoneNumber(userRequestDTO.getPhoneNumber());
//        }
//
//        User updatedUser = userRepository.save(user);
//        return convertToResponseDTO(updatedUser);
//    }
//
//    // Delete user
//    public void deleteUser(Long id) {
//        if (!userRepository.existsById(id)) {
//            throw new RuntimeException("User not found with id: " + id);
//        }
//        userRepository.deleteById(id);
//    }
//
//    // Check if user exists
//    public Boolean userExistsByUsername(String username) {
//        return userRepository.existsByUsername(username);
//    }
//
//    public Boolean userExistsByEmail(String email) {
//        return userRepository.existsByEmail(email);
//    }
//
//    // Convert User entity to UserResponseDTO
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
