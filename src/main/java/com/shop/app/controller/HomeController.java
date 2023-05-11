package com.shop.app.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shop.app.model.entites.Account;
import com.shop.app.service.ProductService;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    ProductService productServiceservice;

    @RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
    public String home(Principal principal, Model model) {
        model.addAttribute("sanphams", productServiceservice.findAll(PageRequest.of(0, 8)).getContent());
        String userName = null;
        if (principal != null) {
            Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("account", account);
            return "public/home";
        }
        model.addAttribute("username", userName);
        return "public/home";
    }
}
