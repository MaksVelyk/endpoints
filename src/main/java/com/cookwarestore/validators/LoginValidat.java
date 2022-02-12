package com.cookwarestore.validators;

import com.cookwarestore.exceptions.AuthValidationExcept;

public class LoginValidat {
    public static void validateLogin(String login) {
        if(login == null || login.length() <= 1) {
            throw new AuthValidationExcept("Login incorrect");
        }
    }

    public static void validatePass(String pass) {
        if(pass == null || pass.length() <= 1) {
            throw new AuthValidationExcept("Password incorrect");
        }
    }
}