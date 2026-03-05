package com.user_service.user_service.mapper;



import com.user_service.user_service.dto.LoginRequestDTO;
import com.user_service.user_service.dto.LoginResponseDTO;
import com.user_service.user_service.model.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    // User → LoginResponseDTO (token est ignoré car pas dans User)
    @Mapping(target = "token", ignore = true)
    LoginResponseDTO toLoginResponseDTO(User user);

    // méthode default pour injecter le token manuellement
    default LoginResponseDTO toLoginResponseDTO(User user, String token) {
        LoginResponseDTO dto = toLoginResponseDTO(user);
        dto.setToken(token);
        return dto;
    }

    // LoginRequestDTO → User
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "watchHistories", ignore = true)
    User toUser(LoginRequestDTO dto);
}