package com.user_service.user_service.service;

import com.user_service.user_service.client.SubscriptionClient;
import com.user_service.user_service.dto.UserRequestDTO;
import com.user_service.user_service.dto.UserResponseDTO;
import com.user_service.user_service.enums.Role;
import com.user_service.user_service.exception.UserAlreadyExistsException;
import com.user_service.user_service.exception.UserNotFoundException;
import com.user_service.user_service.mapper.UserMapper;
import com.user_service.user_service.model.User;
import com.user_service.user_service.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final SubscriptionClient subscriptionClient;

    // ✅ CREATE

    public UserResponseDTO createUser(UserRequestDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new UserAlreadyExistsException("Email déjà utilisé : " + dto.getEmail());
        }
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new UserAlreadyExistsException("Username déjà utilisé : " + dto.getUsername());
        }

        User user = userMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.USER); // rôle par défaut

        return userMapper.toDTO(userRepository.save(user));
    }

    // ✅ GET BY ID

    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Utilisateur introuvable avec l'id : " + id));
        return userMapper.toDTO(user);
    }

    // ✅ GET ALL

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    // ✅ UPDATE

    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Utilisateur introuvable avec l'id : " + id));

        // Vérifier si le nouvel email appartient à un autre utilisateur
        if (!user.getEmail().equals(dto.getEmail()) && userRepository.existsByEmail(dto.getEmail())) {
            throw new UserAlreadyExistsException("Email déjà utilisé : " + dto.getEmail());
        }

        // Vérifier si le nouveau username appartient à un autre utilisateur
        if (!user.getUsername().equals(dto.getUsername()) && userRepository.existsByUsername(dto.getUsername())) {
            throw new UserAlreadyExistsException("Username déjà utilisé : " + dto.getUsername());
        }

        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        return userMapper.toDTO(userRepository.save(user));
    }

    // ✅ DELETE

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("Utilisateur introuvable avec l'id : " + id);
        }
        userRepository.deleteById(id);
    }


    public UserResponseDTO updateRole(Long id, Role role) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Utilisateur introuvable avec l'id : " + id));
        user.setRole(role);
        return userMapper.toDTO(userRepository.save(user));
    }

    public boolean isSubscriptionActive(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Utilisateur introuvable avec l'id : " + userId));
        return Boolean.TRUE.equals(subscriptionClient.isUserActive(userId).get("active"));
    }
}
