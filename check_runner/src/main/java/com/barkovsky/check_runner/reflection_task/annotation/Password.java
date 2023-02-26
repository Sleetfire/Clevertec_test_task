package com.barkovsky.check_runner.reflection_task.annotation;

import com.barkovsky.check_runner.reflection_task.annotation.validation_class.PasswordValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidation.class)
public @interface Password {

    Class<?>[] groups() default {};

    String message() default "The password must contain 10 symbols and one any digit";

    Class<? extends Payload>[] payload() default {};

}
