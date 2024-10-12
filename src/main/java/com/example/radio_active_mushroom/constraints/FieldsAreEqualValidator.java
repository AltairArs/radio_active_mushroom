package com.example.radio_active_mushroom.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldsAreEqualValidator implements ConstraintValidator<FieldsAreEqual, Object> {

    private String field1;

    private String field2;

    @Override
    public void initialize(FieldsAreEqual constraintAnnotation) {
        this.field1 = constraintAnnotation.field1();
        this.field2 = constraintAnnotation.field2();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Object field1_value = new BeanWrapperImpl(o).getPropertyValue(field1);
        Object field2_value = new BeanWrapperImpl(o).getPropertyValue(field2);
        if (field1_value != null) {
            return field1_value.equals(field2_value);
        } else {
            return field2_value == null;
        }
    }
}
