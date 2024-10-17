package com.example.radio_active_mushroom.dto;

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
    private String first_name;
    @NotBlank
    @Size(max = 255)
    private String last_name;
    @NotBlank
    private ThemeColorEnum color;
    @NotBlank
    private ThemeColorizationEnum colorization;
    @NotBlank
    private ThemeModeEnum mode;
}