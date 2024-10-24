package com.example.radio_active_mushroom.dto.document;

import com.example.radio_active_mushroom.constraints.OnlyLettersAndNumbers;
import com.example.radio_active_mushroom.dto.AJAX_Form;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.ObjectError;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateTableDto extends AJAX_Form {
    @Size(min = 3, max = 255)
    @OnlyLettersAndNumbers
    String name;
    String description;
    String friendlyName;
    Integer position_x;
    Integer position_y;
}
