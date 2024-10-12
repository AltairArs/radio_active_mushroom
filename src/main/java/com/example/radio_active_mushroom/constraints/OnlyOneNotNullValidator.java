package com.example.radio_active_mushroom.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class OnlyOneNotNullValidator implements ConstraintValidator<OnlyOneNotNull, Object> {

    private String[] fields;

    @Override
    public void initialize(OnlyOneNotNull constraintAnnotation) {
        this.fields = constraintAnnotation.fields();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        int count = 0;
        for (String field : fields) {
            Object value = new BeanWrapperImpl(o).getPropertyValue(field);
            if (value != null && value != "") {
                count++;
            }
        }
        return count == 1;
    }
}
