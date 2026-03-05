package com.user_service.user_service.dto;

import com.user_service.user_service.enums.Role;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class UserResponseDTO {

    private Long id;
    private String username;
    private String email;
    private Role role;
    private LocalDateTime createdAt;
}
