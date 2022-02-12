package com.cookwarestore.service;


import com.cookwarestore.model.User;

public interface IUserServI {
    User getUserById(int userId);
    void addUser(User user);
}