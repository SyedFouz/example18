package com.example.example18.validations;

import com.example.example18.annotations.PasswordValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

public class PasswordStrengthValidator implements ConstraintValidator<PasswordValidator,String> {

    List<String> weakPasswords;
    @Override
    public void initialize(PasswordValidator constraintAnnotation) {
        weakPasswords= Arrays.asList("12345","password","qwerty");
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s!=null && !(weakPasswords.contains(s));
    }
}
