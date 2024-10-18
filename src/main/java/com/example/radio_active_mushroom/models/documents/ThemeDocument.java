package com.example.radio_active_mushroom.models.documents;

import com.example.radio_active_mushroom.enums.theme.ThemeColorEnum;
import com.example.radio_active_mushroom.enums.theme.ThemeColorizationEnum;
import com.example.radio_active_mushroom.enums.theme.ThemeModeEnum;
import lombok.*;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@TypeAlias(value = "theme")
@Document(collection = "theme_document")
public class ThemeDocument {
    @MongoId
    private String id;

    private String username;

    private ThemeColorEnum color;

    private ThemeColorizationEnum colorization;

    private ThemeModeEnum mode;
}
