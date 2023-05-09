package com.shop.app.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/product-detail")
@Controller
public class ProductDetail {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String product_detail(HttpServletRequest request, Model model) {
        return "user/productDetail";
    }
}
