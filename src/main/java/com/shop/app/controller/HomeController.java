package com.shop.app.controller;

import java.security.Principal;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shop.app.model.Account;

@Controller
@RequestMapping("/home")
public class HomeController {
    
    @RequestMapping(value = {"","/"}, method = RequestMethod.GET)
    public String home(Principal principal, Model model) {
        try {
            String userName = null;
            if (principal != null) {
                Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                model.addAttribute("account", account);
                return "home";
            }
            model.addAttribute("username", userName);
            return "home";
        } catch (Exception e) {
            return "redirect:/403";
        }
    }
}
