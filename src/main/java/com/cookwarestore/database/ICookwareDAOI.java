package com.cookwarestore.database;

import com.cookwarestore.model.Cookware;

import java.util.List;
import java.util.Optional;

public interface ICookwareDAOI {
    List<Cookware> getCookwares();
    Optional<Cookware> getCookwareById(int cookwareId);
    void updateCookware(Cookware cookware);
}
