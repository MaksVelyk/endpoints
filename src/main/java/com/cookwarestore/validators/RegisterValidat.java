package com.cookwarestore.validators;

import com.cookwarestore.exceptions.RegisterValidationExcept;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterValidat {
    public static void validateName(String name) {
        validateBasics(name);
    }

    public static void validateSurname(String surname) {
        validateBasics(surname);
    }

    private static void validateBasics(String value) {
        if(value == null) {
            throw new RegisterValidationExcept();
        }

        Pattern pattern = Pattern.compile("[A-Z][a-z]+");
        Matcher matcher = pattern.matcher(value);

        if(!matcher.matches()) {
            throw new RegisterValidationExcept();
        }
    }
}
