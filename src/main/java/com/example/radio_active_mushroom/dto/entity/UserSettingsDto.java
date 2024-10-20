package com.example.radio_active_mushroom.dto.entity;

import com.example.radio_active_mushroom.enums.theme.ThemeColorEnum;
import com.example.radio_active_mushroom.enums.theme.ThemeColorizationEnum;
import com.example.radio_active_mushroom.enums.theme.ThemeModeEnum;
import com.example.radio_active_mushroom.models.documents.ThemeDocument;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link ThemeDocument}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserSettingsDto implements Serializable {
    @NotBlank
    @Size(max = 255)
    private String firstName;
    @NotBlank
    @Size(max = 255)
    private String lastName;
    @NotBlank
    private ThemeColorEnum color;
    @NotBlank
    private ThemeColorizationEnum colorization;
    @NotBlank
    private ThemeModeEnum mode;
}