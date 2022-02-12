package com.cookwarestore.service.impl;

import com.cookwarestore.database.IUserDAOI;
import com.cookwarestore.model.User;
import com.cookwarestore.service.IUserServI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServ implements IUserServI {

    @Autowired
    IUserDAOI userDAO;

    @Override
    public User getUserById(int userId) {
        Optional<User> userOptional = userDAO.getUserById(userId);

        if (userOptional.isEmpty()) {
            return null;
        }

        return userOptional.get();
    }

    @Override
    public void addUser(User user) {
        this.userDAO.addUser(user);

    }
}