package com.user_service.user_service.service;

import com.user_service.user_service.dto.LoginRequestDTO;
import com.user_service.user_service.dto.LoginResponseDTO;
import com.user_service.user_service.dto.UserRequestDTO;
import com.user_service.user_service.dto.UserResponseDTO;
import com.user_service.user_service.enums.Role;
import com.user_service.user_service.mapper.AuthMapper;
import com.user_service.user_service.mapper.UserMapper;
import com.user_service.user_service.model.User;
import com.user_service.user_service.repository.UserRepository;
import com.user_service.user_service.security.JwtService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private  final AuthMapper authMapper;

    public LoginResponseDTO register(LoginRequestDTO request) {
        // Vérifier si username ou email déjà pris
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username déjà utilisé");
        }
        if (userRepository.existsByEmail(request.getUsername())) {
            throw new RuntimeException("Email déjà utilisé");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER) // rôle par défaut
                .build();

        userRepository.save(user);

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
        );

        String token = jwtService.generateToken(userDetails);
        return authMapper.toLoginResponseDTO(user,token);
    }

    public LoginResponseDTO login(LoginRequestDTO request) {
        // Lance une exception si credentials invalides
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
        );

        String token = jwtService.generateToken(userDetails);
        return authMapper.toLoginResponseDTO(user,token);
    }
}

