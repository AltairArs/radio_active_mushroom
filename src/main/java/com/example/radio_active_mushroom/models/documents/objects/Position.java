package com.example.radio_active_mushroom.models.documents.objects;

import lombok.*;
import org.springframework.data.annotation.TypeAlias;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@TypeAlias(value = "position")
public class Position {
    private Integer x;
    private Integer y;
}
