package com.bioqwer.serverApp.security;

import com.bioqwer.serverApp.model.User;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by Antony on 23.10.2014.
 */

public class ValidationUtil {

    public static boolean isValidEmail(User user) {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(user);
        return constraintViolations.isEmpty();
    }

    public static boolean isValidPassword(User user) {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(user);
        return constraintViolations.isEmpty();
    }

    public static boolean isValid(User user) {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(user);

        if (!constraintViolations.isEmpty()) {
            String errorsLog = new String();
            errorsLog += "Properties not valid = " + constraintViolations.size() + "\n";
            for (ConstraintViolation<Object> cv : constraintViolations)
                errorsLog += String.format("property: [%s], value: [%s], message: [%s]\n", cv.getPropertyPath(), cv.getInvalidValue(), cv.getMessage());
            throw new RuntimeException(errorsLog);
        }
        return false;
    }

}
