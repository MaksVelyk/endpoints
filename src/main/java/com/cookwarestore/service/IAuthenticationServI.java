package com.cookwarestore.service;

import com.cookwarestore.model.view.RegisterUser;

public interface IAuthenticationServI {
    void authenticate(String login, String password);
    void register(RegisterUser registerUser);
}
