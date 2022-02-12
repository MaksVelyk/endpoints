package com.cookwarestore.service;

import com.cookwarestore.model.Cookware;

import java.util.List;

public interface ICookwareServI {
    List<Cookware> getAllCookwares();
    Cookware getCookwareById(int cookwareId);
    void updateCookware(Cookware cookware);
}
