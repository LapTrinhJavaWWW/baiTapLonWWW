package com.shop.app.controller;

import com.shop.app.model.Cart;
import com.shop.app.model.Product;
import com.shop.app.service.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.*;

@Controller
@SessionAttributes("/cart")
public class CartController  {



    @Autowired
    ProductService productServiceservice;


//    @RequestMapping(value = "/cart", method = RequestMethod.GET)
//    public String cartPage() {
//        return "user/cart.html";
//    }



    @PostMapping("/add-to-cart")
    public ResponseEntity<?> addToCart(HttpServletRequest request, Model model, HttpSession session) {
        String id = request.getParameter("id");
        Cart cart = new Cart();
        Product  product = productServiceservice.findById(Integer.valueOf(id));
        cart.addItem(product);

       session.setAttribute("cart",cart);

        System.out.printf("Kaiwin ADD TO CART");
        System.out.println(cart);

        return ResponseEntity.ok("ok");
    }

    @GetMapping("/cart")
    public String showCart(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        // Lấy đối tượng Cart từ session
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            // Nếu giỏ hàng chưa tồn tại, tạo mới một đối tượng Cart
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        // Thêm đối tượng Cart vào model

        model.addAttribute("cart", cart);
        // Trả về trang cart.html
        return "user/cart";
    }
}
