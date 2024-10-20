package com.example.radio_active_mushroom.constraints.validators;

import com.example.radio_active_mushroom.constraints.FieldsAreEqual;
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
        Object field1Value = new BeanWrapperImpl(o).getPropertyValue(field1);
        Object field2Value = new BeanWrapperImpl(o).getPropertyValue(field2);
        if (field1Value != null) {
            return field1Value.equals(field2Value);
        } else {
            return field2Value == null;
        }
    }
}
