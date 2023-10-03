package com.example.example18.annotations;

import com.example.example18.validations.FieldsValueMatchValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Email;

import java.lang.annotation.*;

@Constraint(
        validatedBy = {FieldsValueMatchValidator.class}
)
@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)

public @interface FieldsValueMatch {

    String message() default "Field Values don't match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String field();

    String fieldMatch();

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
     @interface List {
        FieldsValueMatch[] value();
    }
}
