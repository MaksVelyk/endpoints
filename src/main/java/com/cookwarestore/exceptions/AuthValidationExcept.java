package com.cookwarestore.exceptions;

public class AuthValidationExcept extends RuntimeException {
    private String info;

    public AuthValidationExcept(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
