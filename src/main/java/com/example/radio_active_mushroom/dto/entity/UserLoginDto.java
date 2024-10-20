package com.example.radio_active_mushroom.dto.entity;

import com.example.radio_active_mushroom.constraints.OnlyLettersAndNumbers;
import com.example.radio_active_mushroom.constraints.OnlyOneNotNull;
import com.example.radio_active_mushroom.models.entity.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link UserEntity}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@OnlyOneNotNull(
        message = "Только одно должно быть заполнено: Email, Ник",
        fields = {
                "username",
                "email"
        }
)
public class UserLoginDto implements Serializable {
    @OnlyLettersAndNumbers
    @Size(max = 255)
    private String username;
    @NotBlank
    @OnlyLettersAndNumbers
    @Size(min = 8, max = 255)
    private String password;
    @Email
    @Size(max = 255)
    private String email;
}