package com.example.radio_active_mushroom.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OnlyLettersAndNumbersValidator implements ConstraintValidator<OnlyLettersAndNumbers, String> {

    @Override
    public void initialize(OnlyLettersAndNumbers constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null && s.matches("^[\\w]+$") || s == null || s.isEmpty();
    }
}
