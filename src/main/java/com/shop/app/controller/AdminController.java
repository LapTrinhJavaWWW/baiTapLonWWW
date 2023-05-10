package com.shop.app.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shop.app.model.entites.Product;
import com.shop.app.service.impl.ProductServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    ProductServiceImpl productServiceservice;

    // private final UserRepository repository;

    List<Product> ds = new ArrayList<Product>();

    @RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
    public ModelAndView admin() {
        ModelAndView modelAndView = new ModelAndView("admin/product");
        modelAndView.addObject("products", productServiceservice.findAll());
        return modelAndView;
    }

    // get page product
    @RequestMapping(value = { "/product" }, method = RequestMethod.GET)
    public ModelAndView product(HttpServletRequest request, Model model) {
        ModelAndView modelAndView = new ModelAndView("admin/product");
        modelAndView.addObject("products", productServiceservice.findAll());
        return modelAndView;
    }

    // save product
    @RequestMapping(value = { "/product/save" }, method = RequestMethod.GET)
    public String saveproduct(HttpServletRequest request, Model model) {
        String name = request.getParameter("name");
        String slug = request.getParameter("slug");
        double price = Double.parseDouble(request.getParameter("price"));
        String img = request.getParameter("img");
        Set<String> imgList = new HashSet<String>();
        imgList.add(img);
        double sceen = Double.parseDouble(request.getParameter("screen"));
        String chip = request.getParameter("chip");
        int ram = Integer.parseInt(request.getParameter("ram"));
        int rom = Integer.parseInt(request.getParameter("rom"));
        String bestSeller = request.getParameter("bestSeller");
        String type = request.getParameter("type");

        Product product = new Product();
        product.setName(name);
        product.setSlug(slug);
        product.setPrice(price);
        product.setImg(imgList);
        product.setScreen(sceen);
        product.setChip(chip);
        product.setRam(ram);
        product.setRom(rom);
        product.setBestSeller(bestSeller);
        product.setType(type);

        productServiceservice.save(product);

        return "redirect:/admin/product";
    }

    // delete product
    @RequestMapping(value = { "/product/deleted{id}" }, method = RequestMethod.GET)
    public String deleteProduct(HttpServletRequest request) {
        String id = request.getParameter("id");
        productServiceservice.deleteById(Integer.parseInt(id));
        return "redirect:/admin/product";
    }

    // get product by id
    @RequestMapping(value = { "/product/getproduct" }, method = RequestMethod.POST)
    public ModelAndView getProductById(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/product");
        String id = request.getParameter("id");
        Product product = productServiceservice.findById(Integer.parseInt(id));
        System.out.println(product);
        modelAndView.addObject("product", product);

        return modelAndView;
    }

    // update product
    @RequestMapping(value = { "/product/update" }, method = RequestMethod.GET)
    public String UpdateProduct(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String slug = request.getParameter("slug");
        double price = Double.parseDouble(request.getParameter("price"));
        String img = request.getParameter("img");
        Set<String> imgList = new HashSet<String>();
        imgList.add(img);
        double sceen = Double.parseDouble(request.getParameter("screen"));
        String chip = request.getParameter("chip");
        int ram = Integer.parseInt(request.getParameter("ram"));
        int rom = Integer.parseInt(request.getParameter("rom"));
        String bestSeller = request.getParameter("bestSeller");
        String type = request.getParameter("type");

        Product p = productServiceservice.findById(Integer.parseInt(id));
        p.setName(name);
        p.setSlug(slug);
        p.setPrice(price);
        p.setImg(imgList);
        p.setScreen(sceen);
        p.setChip(chip);
        p.setRam(ram);
        p.setRom(rom);
        p.setBestSeller(bestSeller);
        p.setType(type);

        productServiceservice.save(p);

        return "redirect:/admin/product";
    }

    // search product
    @RequestMapping(value = { "/product/search" }, method = RequestMethod.GET)
    public ModelAndView searchProduct(HttpServletRequest request, Model model) {
        ModelAndView modelAndView = new ModelAndView("admin/product");
        String name = request.getParameter("name");
        modelAndView.addObject("products", productServiceservice.findByName(name));
        return modelAndView;
    }

    @RequestMapping(value = { "/user" }, method = RequestMethod.GET)
    public ModelAndView user(HttpServletRequest request, Model model) {
        ModelAndView modelAndView = new ModelAndView("admin/user");
        return modelAndView;
    }

}
