package com.example.radio_active_mushroom.dto;

import com.example.radio_active_mushroom.constraints.FieldsAreEqual;
import com.example.radio_active_mushroom.constraints.OnlyLettersAndNumbers;
import com.example.radio_active_mushroom.models.entity.UserEntity;
import jakarta.validation.constraints.*;
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
@FieldsAreEqual(
        field1 = "password",
        field2 = "confirm_password",
        message = "Пароли должны совпадать"
)
public class UserRegistrationDto implements Serializable {
    @NotBlank
    @OnlyLettersAndNumbers
    @Size(min = 3, max = 255)
    private String username;
    @NotBlank
    @OnlyLettersAndNumbers
    @Size(min = 8, max = 255)
    private String password;
    @NotBlank
    @Email
    @Size(max = 255)
    private String email;
    @NotBlank
    @Size(max = 255)
    private String first_name;
    @NotBlank
    @Size(max = 255)
    private String last_name;
    @NotBlank
    @OnlyLettersAndNumbers
    @Size(max = 255)
    private String confirm_password;
}