package com.example.radio_active_mushroom.dto.document;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateTable implements Serializable {
    String name;
    String description;
    String friendlyName;
}
