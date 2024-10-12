package com.example.radio_active_mushroom.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.example.radio_active_mushroom.models.UserEntity}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRegistrationDto implements Serializable {
    private String username;
    private String password;
    private String email;
    private String first_name;
    private String last_name;
    private String confirm_password;
}