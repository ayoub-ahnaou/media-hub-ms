package com.user_service.user_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class UserRequestDTO {


    private String username;


    private String email;

    private String password;
}
