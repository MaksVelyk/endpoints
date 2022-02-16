package com.cookwarestore.rest;

import com.cookwarestore.model.Cookware;
import com.cookwarestore.model.User;
import com.cookwarestore.service.IUserServI;
import com.cookwarestore.service.ICookwareServI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest")
public class RestControll {

    @Autowired
    ICookwareServI iCookwareServI;

    @Autowired
    IUserServI iUserService;

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable int id) {
        User user = iUserService.getUserById(id);

        return user;
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public void addUser(@RequestBody User user) {iUserService.addUser(user);}

    @RequestMapping(value = "/cookware/{id}", method = RequestMethod.GET)
    public Cookware getCookware(@PathVariable int id) {
        Cookware cookware = iCookwareServI.getCookwareById(id);
        return cookware;
    }

    @RequestMapping(value = "/cookware/getall", method = RequestMethod.GET)
    public List<Cookware> getCookwares() {
        return iCookwareServI.getAllCookwares();
    }

    @RequestMapping(value = "/cookware/update", method = RequestMethod.POST)
    public void updateCookware(@RequestBody Cookware cookware) {
        iCookwareServI.updateCookware(cookware);
    }
}
