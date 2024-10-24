package com.example.radio_active_mushroom.dto;

import lombok.*;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AJAX_Form implements Serializable {
    protected Map<String, String> errors;

    public void setErrorsFromErrorList(List<ObjectError> objectErrors){
        clearError();
        for(ObjectError objectError : objectErrors){
            errors.put(((FieldError) objectError).getField(), objectError.getDefaultMessage());
        }
    }

    public void clearError() {
        errors = new HashMap<>();
    }
}
