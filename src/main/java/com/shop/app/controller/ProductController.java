package com.shop.app.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shop.app.model.Account;
import com.shop.app.model.ListProduct;
import com.shop.app.model.Product;
import com.shop.app.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/san-pham")
public class ProductController {

    @Autowired
    ProductService productServiceservice;

    ListProduct ds = new ListProduct();

    @RequestMapping(value = {"/dien-thoai"}, method = RequestMethod.GET)
    public String userAccountInfor(Principal principal, Model model) {
        try {
            String userName = null;
            ds.setDs(productServiceservice.findAll());
            if (principal != null) {
                Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                model.addAttribute("account", account);
                model.addAttribute("sanphams", productServiceservice.findAll());
                return "user/product";
            }
            model.addAttribute("sanphams", productServiceservice.findAll());
            model.addAttribute("username", userName);
            return "user/product";
        } catch (Exception e) {
            return "redirect:/403";
        }
    }

    @RequestMapping(value = "/fillter", method = RequestMethod.POST)
    public ResponseEntity<List<Product>> fillterProduct(HttpServletRequest request, Model model,HttpServletResponse response) {
        String brand = request.getParameter("hang-san-xuat");
        List<String> dsbrands = brand != null ? List.of(brand.split(",")) : new ArrayList<>();
        int minPrice = 0;
        int maxPrice = 0;
        if(request.getParameter("minPrice") == null || request.getParameter("maxPrice") == null) {
            minPrice = 0;
            maxPrice = 0;
        }else{
            minPrice = Integer.parseInt(request.getParameter("minPrice"));
            maxPrice = Integer.parseInt(request.getParameter("maxPrice"));
        }
        return ResponseEntity.status(200).body(ds.fillter(dsbrands, minPrice, maxPrice, brand));
    }

    @RequestMapping(value = "/fillter", method = RequestMethod.GET)
    public String refreshFillter(){
        return "redirect:/san-pham/dien-thoai";
    }
   
    // @RequestMapping(value = "/fillter", method = RequestMethod.GET)
    // public ResponseEntity<List<Product>> fillterProduct(HttpServletRequest request, Model model,HttpServletResponse response) {
    //     String brand = request.getParameter("brands");
    //     List<String> dsbrands = brand != null ? List.of(brand.split(",")) : new ArrayList<>();
    //     int minPrice = 0;
    //     int maxPrice = 0;
    //     if(request.getParameter("minPrice") == null || request.getParameter("maxPrice") == null) {
    //         minPrice = 0;
    //         maxPrice = 0;
    //     }else{
    //         minPrice = Integer.parseInt(request.getParameter("minPrice"));
    //         maxPrice = Integer.parseInt(request.getParameter("maxPrice"));
    //     }
    //     return ResponseEntity.status(200).body(ds.fillter(dsbrands, minPrice, maxPrice, brand));
    // }

    @PostMapping(value = "/sort/giathap")
    public ResponseEntity<?> getSortMin(HttpServletRequest request,Model model) {
        List<Product> dsFillter = new ArrayList<>();
        dsFillter = ds.sortPriceMin();
        return ResponseEntity.ok().body(dsFillter);
    }
    @PostMapping(value = "/sort/giacao")
    public ResponseEntity<?> getSortMax(HttpServletRequest request,Model model) {
        List<Product> dsFillter = new ArrayList<>();
        dsFillter = ds.sortPriceMax();
        return ResponseEntity.ok().body(dsFillter);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchProduct(HttpServletRequest request, Model model) {
        String searchVal = request.getParameter("searchVal");
        List<Product> dsFillter = new ArrayList<>();
        dsFillter = ds.search(searchVal);
        model.addAttribute("sanphams", dsFillter);
        return "user/product";
    }
}








