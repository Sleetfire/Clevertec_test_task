package com.barkovsky.check_runner.reflection_json.reflection_task.annotation.validation_class;

import com.barkovsky.check_runner.reflection_json.reflection_task.annotation.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidation implements ConstraintValidator<Password, char[]> {

    @Override
    public boolean isValid(char[] value, ConstraintValidatorContext context) {
        if (value == null || value.length < 10) {
            return false;
        }

        boolean isContainDigit = false;

        for (char c : value) {
            if (Character.isDigit(c)) {
                isContainDigit = true;
            }
        }
        return isContainDigit;
    }
}
