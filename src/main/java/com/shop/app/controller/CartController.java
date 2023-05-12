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
import java.text.DecimalFormat;
import java.util.*;

@Controller
@SessionAttributes("/cart")
public class CartController  {



    @Autowired
    ProductService productServiceservice;





    @PostMapping("/add-to-cart")
    public ResponseEntity<?> addToCart(HttpServletRequest request, Model model, HttpSession session) {
        String id = request.getParameter("id");
        Cart cart = (Cart) session.getAttribute("cart");


        if (cart == null) {
            // Nếu giỏ hàng chưa tồn tại, tạo một giỏ hàng mới
            cart = new Cart();
        }
        // Lấy danh sách sản phẩm hiện có trong giỏ hàng
        List<Product> items = cart.getItems();

        // Lấy thông tin sản phẩm mới dựa trên id
        Product product = productServiceservice.findById(Integer.valueOf(id));
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        double price = product.getPrice();
        System.out.println("Price: "+ decimalFormat.format(price));

        // Thêm sản phẩm mới vào danh sách sản phẩm trong giỏ hàng
        items.add(product);

        // Cập nhật lại danh sách sản phẩm trong giỏ hàng
        cart.setItems(items);

        // Lưu giỏ hàng vào session

        session.setAttribute("cart", cart);
        session.setAttribute("price",  decimalFormat.format(price));

        System.out.println(cart);

        return ResponseEntity.ok("ok");
    }

    @GetMapping("/cart")
    public String showCart(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        // Lấy đối tượng Cart từ session
        Cart cart = (Cart) session.getAttribute("cart");
        String price = (String) session.getAttribute("price");
        if (cart == null) {
            // Nếu giỏ hàng chưa tồn tại, tạo mới một đối tượng Cart
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        // Thêm đối tượng Cart vào model

        model.addAttribute("cart", cart);
        model.addAttribute("price", price);
        // Trả về trang cart.html
        return "user/cart";
    }
}
