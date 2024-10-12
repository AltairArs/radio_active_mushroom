package com.example.radio_active_mushroom.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class OnlyOneNotNullValidator implements ConstraintValidator<OnlyOneNotNull, Object> {

    private String[] fields;

    @Override
    public void initialize(OnlyOneNotNull constraintAnnotation) {
        this.fields = constraintAnnotation.fields();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        int count = 0;
        for (String field : fields) {
            if (new BeanWrapperImpl(o).getPropertyValue(field) != null) {
                count++;
            }
        }
        return count == 1;
    }
}
