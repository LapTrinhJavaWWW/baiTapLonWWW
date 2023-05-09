package com.shop.app.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shop.app.model.entites.Account;
import com.shop.app.model.entites.Breadcrumb;
import com.shop.app.model.entites.ListProduct;
import com.shop.app.model.entites.Product;
import com.shop.app.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/san-pham")
public class ProductController {

    @Autowired
    ProductService productServiceservice;

    ListProduct ds = new ListProduct();

    @RequestMapping(value = { "/dien-thoai" }, method = RequestMethod.GET)
    public String userAccountInfor(Principal principal, Model model) {
        try {
            String userName = null;
            ds.setDs(productServiceservice.findAll());
            model.addAttribute("sanphams", ds.getDs());
            model.addAttribute("breadcrumb", "Điện thoại");
            model.addAttribute("href", "/san-pham/dien-thoai");
            if (principal != null) {
                Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                model.addAttribute("account", account);
                return "user/product";
            }
            model.addAttribute("username", userName);
            return "user/product";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/403";
        }
    }

    @RequestMapping(value = "/fillter", method = RequestMethod.POST)
    public ResponseEntity<List<Product>> fillterProduct(HttpServletRequest request, Model model,
            HttpServletResponse response) {
        String brand = request.getParameter("hang-san-xuat");
        List<String> dsbrands = brand != null ? List.of(brand.split(",")) : new ArrayList<>();
        int minPrice = 0;
        int maxPrice = 0;
        if (request.getParameter("minPrice") == null || request.getParameter("maxPrice") == null) {
            minPrice = 0;
            maxPrice = 0;
        } else {
            minPrice = Integer.parseInt(request.getParameter("minPrice"));
            maxPrice = Integer.parseInt(request.getParameter("maxPrice"));
        }
        return ResponseEntity.status(200).body(ds.fillter(dsbrands, minPrice, maxPrice, brand));
    }

    @RequestMapping(value = "/fillter", method = RequestMethod.GET)
    public String refreshFillter() {
        return "redirect:/san-pham/dien-thoai";
    }

    @PostMapping(value = "/sort/giathap")
    public ResponseEntity<?> getSortMin(HttpServletRequest request, Model model) {
        List<Product> dsFillter = new ArrayList<>();
        dsFillter = ds.sortPriceMin();
        return ResponseEntity.ok().body(dsFillter);
    }

    @PostMapping(value = "/sort/giacao")
    public ResponseEntity<?> getSortMax(HttpServletRequest request, Model model) {
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

    @RequestMapping(value = "/dien-thoai/{nameproduct}", method = RequestMethod.GET)
    public ModelAndView product_detail(Principal principal, HttpServletRequest request, Model model,
            @PathVariable("nameproduct") String nameproduct) {

        try {
            String userName = null;
            ModelAndView modelAndView = new ModelAndView("user/productDetail");
            Product product = productServiceservice.findByName(nameproduct).get(0);
            modelAndView.addObject("product", product);
            modelAndView.addObject("originalPrice", product.getPrice() * 0.8);

            List<Breadcrumb> breadcrumbs = new ArrayList<>();
            breadcrumbs.add(new Breadcrumb("Trang chủ", "/home"));
            breadcrumbs.add(new Breadcrumb("Điện thoại", "/san-pham/dien-thoai"));
            breadcrumbs.add(new Breadcrumb("Chi tiết sản phẩm", "/san-pham/dien-thoai/" + product.getName()));

            modelAndView.addObject("breadcrumbs", breadcrumbs);

            if (principal != null) {
                Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                modelAndView.addObject("account", account);
                return modelAndView;
            }
            model.addAttribute("username", userName);
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            ModelAndView modelAndView = new ModelAndView("exception/403");
            return modelAndView;
        }
    }

    // get page product detail
    // @RequestMapping(value = "/dien-thoai/{nameproduct}", method =
    // RequestMethod.GET)
    // public ModelAndView product_detail(HttpServletRequest request, Model model,
    // @PathVariable("nameproduct") String nameproduct) {
    // ModelAndView modelAndView = new ModelAndView("user/productDetail");
    // Product product = productServiceservice.findByName(nameproduct);
    // System.out.println();
    // return modelAndView;
    // }

}
