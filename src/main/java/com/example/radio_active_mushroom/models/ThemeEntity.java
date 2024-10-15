package com.example.radio_active_mushroom.models;

import com.example.radio_active_mushroom.enums.ThemeColorEnum;
import com.example.radio_active_mushroom.enums.ThemeColorizationEnum;
import com.example.radio_active_mushroom.enums.ThemeModeEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Document("theme_entity")
public class ThemeEntity {
    @Id
    @Column(name = "username", nullable = false)
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(name = "color", nullable = false, length = 20)
    private ThemeColorEnum color;

    @Enumerated(EnumType.STRING)
    @Column(name = "colorization", nullable = false, length = 4)
    private ThemeColorizationEnum colorization;

    @Enumerated(EnumType.STRING)
    @Column(name = "mode", nullable = false, length = 5)
    private ThemeModeEnum mode;

}
