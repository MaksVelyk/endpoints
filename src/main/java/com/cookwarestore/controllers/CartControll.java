package com.cookwarestore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.cookwarestore.service.ICartServI;
import com.cookwarestore.session.SessionObject;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/cart")
public class CartControll {

    @Autowired
    ICartServI cartService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/add/{cookwareId}")
    public String addCookwareToCart(@PathVariable Integer cookwareId) {
        this.cartService.addCookwareToCart(cookwareId);
        return "redirect:/main";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String cart(Model model) {
        model.addAttribute("cart",
                this.sessionObject.getCart());
        model.addAttribute("sum", this.sessionObject.getCart().getSum());
        model.addAttribute("logged", this.sessionObject.isLogged());

        return "cart";
    }
}