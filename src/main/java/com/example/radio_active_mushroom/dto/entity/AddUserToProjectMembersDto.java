package com.example.radio_active_mushroom.dto.entity;

import com.example.radio_active_mushroom.constraints.OnlyLettersAndNumbers;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AddUserToProjectMembersDto {
    @NotBlank
    @OnlyLettersAndNumbers
    @Size(max = 255)
    private String username;
}
