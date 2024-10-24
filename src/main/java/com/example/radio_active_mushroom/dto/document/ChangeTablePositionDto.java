package com.example.radio_active_mushroom.dto.document;

import com.example.radio_active_mushroom.models.documents.objects.Position;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ChangeTablePositionDto {
    private Integer x;
    private Integer y;
    private String tableName;

    public Position toPosition() {
        return new Position(x, y);
    }
}
