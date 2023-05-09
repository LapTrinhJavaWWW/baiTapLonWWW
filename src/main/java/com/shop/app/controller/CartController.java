package com.shop.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/cart")
public class CartController {
    
    @RequestMapping(value ={"","/"}, method = RequestMethod.GET)
    public String userinfoCart() {
        return "user/cart";
    }
}
