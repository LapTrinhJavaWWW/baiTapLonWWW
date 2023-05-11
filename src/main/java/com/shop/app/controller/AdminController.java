package com.shop.app.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shop.app.model.entites.Account;
import com.shop.app.model.entites.Product;
import com.shop.app.service.impl.ProductServiceImpl;
import com.shop.app.service.impl.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    UserServiceImpl userService;

    @RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
    public ModelAndView admin(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/product");
        List<Integer> pages = new ArrayList<Integer>();
        for (int i = 1; i <= (productService.countProduct() / 5) + 1; i++) {
            pages.add(i);
        }
        modelAndView.addObject("pages", pages);

        return modelAndView;
    }

    // get page product/refresh page
    @RequestMapping(value = { "/product", "/product/refresh" }, method = RequestMethod.GET)
    public ModelAndView product(HttpServletRequest request, Model model) {
        ModelAndView modelAndView = new ModelAndView("admin/product");

        Page<Product> page = productService.findAll(PageRequest.of(0, 4));
        List<Integer> listPage = new ArrayList<Integer>();
        for (int i = 0; i < page.getTotalPages(); i++) {
            listPage.add(i);
        }
        modelAndView.addObject("pages", listPage);
        modelAndView.addObject("products", page.getContent());

        return modelAndView;
    }

    // search product
    @RequestMapping(value = { "/product/search" }, method = RequestMethod.POST)
    public ResponseEntity<?> searchProduct(HttpServletRequest request, Model model) {

        String name = request.getParameter("search").trim();

        List<Product> ds = productService.findBySlugContainingOrNameContainingOrTypeContaining(name, name, name);

        return ResponseEntity.ok(ds);

    }

    // get pagination product
    @RequestMapping(value = { "/product" }, method = RequestMethod.POST)
    public ResponseEntity<?> productDs(HttpServletRequest request, Model model) {
        int indexPage = Integer.parseInt(request.getParameter("page"));
        Page<Product> page = productService.findAll(PageRequest.of(indexPage, 4));

        return ResponseEntity.ok(page.getContent());
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

        productService.save(product);

        return "redirect:/admin/product";
    }

    // delete product
    @RequestMapping(value = { "/product/deleted{id}" }, method = RequestMethod.GET)
    public String deleteProduct(HttpServletRequest request) {
        String id = request.getParameter("id");
        productService.deleteById(Integer.parseInt(id));
        return "redirect:/admin/product";
    }

    // get product by id
    @RequestMapping(value = { "/product/getproduct" }, method = RequestMethod.POST)
    public ModelAndView getProductById(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/product");
        String id = request.getParameter("id");
        Product product = productService.findById(Integer.parseInt(id));
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

        Product p = productService.findById(Integer.parseInt(id));
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

        productService.save(p);

        return "redirect:/admin/product";
    }

    // get page user/refresh page
    @RequestMapping(value = { "/user", "/user/refresh" }, method = RequestMethod.GET)
    public ModelAndView user(HttpServletRequest request, Model model) {
        ModelAndView modelAndView = new ModelAndView("admin/user");

        Page<Account> page = userService.findAll(PageRequest.of(0, 4));
        List<Integer> listPage = new ArrayList<Integer>();
        for (int i = 0; i < page.getTotalPages(); i++) {
            listPage.add(i);
        }
        modelAndView.addObject("pages", listPage);
        modelAndView.addObject("users", page.getContent());

        return modelAndView;
    }

    // search user
    @RequestMapping(value = { "/user/search" }, method = RequestMethod.POST)
    public ResponseEntity<?> searchUser(HttpServletRequest request, Model model) {

        String name = request.getParameter("search").trim();

        List<Account> ds = userService.findByLastNameContainingOrFirstNameContaining(name, name);

        return ResponseEntity.ok(ds);
    }

}
