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

        // Lấy thông tin sản phẩm mới dựa trên id
        Product product = productServiceservice.findById(Integer.valueOf(id));
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        decimalFormat.setGroupingUsed(false);
        double price = product.getPrice();

        String formattedPrice = decimalFormat.format(price); // Chuỗi đã định dạng có dấu phẩy
        formattedPrice = formattedPrice.replace(",", ""); // Loại bỏ dấu phẩy
        double parsedPrice = Double.parseDouble(formattedPrice); // Chuyển đổi thành số double
        product.setPrice(parsedPrice);


        // Cập nhật lại danh sách sản phẩm trong giỏ hàng


        cart.addItem(product);
        System.out.println("Kaiiwinnnnn");
        System.out.println(cart);
        // Lưu giỏ hàng vào session

        session.setAttribute("cart", cart);

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
