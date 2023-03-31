package com.monthlycostsorganizer.api.services;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.stereotype.Component;

@Component
public class ValidationService<T> {

    private String message;

    public ValidationService() {
        this.message = null;
    }

    public boolean isValid(T body) {
        ValidatorFactory validFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validFactory.getValidator();

        Set<ConstraintViolation<T>> violations = validator.validate(body);

        if (violations.size() > 0) {
            StringBuilder errorMessage = new StringBuilder();
            for (ConstraintViolation<T> violation : violations) {
                errorMessage.append(String.format("Error: %s\n", violation.getMessage()));
            }

            this.message = errorMessage.toString();

            return false;
        }

        return true;
    }

    public String getMessage() {
        return this.message;
    }
}
