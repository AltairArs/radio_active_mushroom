package com.example.radio_active_mushroom.dto;

import com.example.radio_active_mushroom.constraints.OnlyOneNotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
@ToString
public class UserLoginDto implements Serializable {
    private String username;
    private String password;
    private String email;
}