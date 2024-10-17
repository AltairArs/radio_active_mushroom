package com.example.radio_active_mushroom.models.documents;

import com.example.radio_active_mushroom.enums.theme.ThemeColorEnum;
import com.example.radio_active_mushroom.enums.theme.ThemeColorizationEnum;
import com.example.radio_active_mushroom.enums.theme.ThemeModeEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "theme_entity")
public class ThemeDocument {
    @Id
    private String id;

    private String username;

    private ThemeColorEnum color;

    private ThemeColorizationEnum colorization;

    private ThemeModeEnum mode;

}
