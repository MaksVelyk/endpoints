package com.cookwarestore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.cookwarestore.exceptions.AuthValidationExcept;
import com.cookwarestore.exceptions.LoginAlreadyUseExcept;
import com.cookwarestore.model.view.RegisterUser;
import com.cookwarestore.service.IAuthenticationServI;
import com.cookwarestore.session.SessionObject;
import com.cookwarestore.validators.LoginValidat;
import com.cookwarestore.validators.RegisterValidat;

import javax.annotation.Resource;

@Controller
public class AuthenticationControll {

    @Autowired
    IAuthenticationServI authenticateService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(Model model) {
        model.addAttribute("logged", this.sessionObject.isLogged());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String login, @RequestParam String password) {
        try {
            LoginValidat.validateLogin(login);
            LoginValidat.validatePass(password);
        } catch (AuthValidationExcept e) {
            return "redirect:/login";
        }

        this.authenticateService.authenticate(login, password);

        if(this.sessionObject.isLogged()) {
            return "redirect:/main";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        this.sessionObject.setUser(null);
        return "redirect:/main";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("logged", this.sessionObject.isLogged());
        model.addAttribute("ruser", new RegisterUser());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute RegisterUser registerUser) {
        try {
            RegisterValidat.validateName(registerUser.getName());
            RegisterValidat.validateSurname(registerUser.getSurname());
            LoginValidat.validateLogin(registerUser.getLogin());
            LoginValidat.validatePass(registerUser.getPass());
            checkPasswords(registerUser.getPass(), registerUser.getPassword2());
            this.authenticateService.register(registerUser);
        } catch (AuthValidationExcept | LoginAlreadyUseExcept e) {
            return "redirect:/register";
        }

        return "redirect:/main";
    }

    private void checkPasswords(String pass1, String pass2) {
        if(pass1 == null || !pass1.equals(pass2)) {
            throw new AuthValidationExcept("Incorrect passwords !");
        }
    }
}