package com.shop.app.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shop.app.model.Product;
import com.shop.app.repository.UserRepository;
import com.shop.app.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    ProductService productServiceservice;

    private final UserRepository repository;

    List<Product> ds = new ArrayList<Product>();

    @RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
    public String admin() {
        return "admin/index";
    }

    @RequestMapping(value = { "/tag" }, method = RequestMethod.GET)
    public ResponseEntity<?> product(HttpServletRequest request, Model model) {
        String open = request.getParameter("open");
        if (open.equals("user")) {
            return ResponseEntity.ok().body(repository.findAll());
        } else if (open.equals("product")) {
            return ResponseEntity.ok().body(productServiceservice.findAll());
        }
        return null;
    }

    @RequestMapping(value = { "/product" }, method = RequestMethod.GET)
    public ResponseEntity<?> saveproduct(HttpServletRequest request, Model model) {
        String action = request.getParameter("action");
        if (action.equals("save")) {
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
        }

        return ResponseEntity.ok().body("Thêm thành công");
    }
    // @RequestMapping(value = {"/",""},method = RequestMethod.GET)
    // public String openAdmin(Model model,HttpServletRequest request,Principal
    // principal) {
    // if (principal == null || !principal.getName().equals("admin@gmail.com")) {
    // return "redirect:/login";
    // }else{
    // model.addAttribute("products", productServiceservice.findAll());
    // model.addAttribute("usernames", principal.getName());
    // return "admin/home";
    // }
    // }

    // @GetMapping("/search/{valueSearch}")
    // public String search(@PathVariable(name = "valueSearch") String
    // valueSearch,Model model,HttpServletRequest request) {
    // HttpSession session = request.getSession(true);
    // Object usernames = session.getAttribute("username");
    // if (usernames == null) {
    // return "redirect:/auth/login";
    // }
    // List<Product> dsSearch = new ArrayList<Product>();
    // String search = valueSearch.toLowerCase().trim();
    // productServiceservice.findAll().forEach(e -> {
    // if (e.getName().toLowerCase().trim().contains(search)) {
    // dsSearch.add(e);
    // }
    // });
    // model.addAttribute("products", dsSearch);
    // return "admin/home";
    // }

    // @GetMapping("/product/delete/{id}")
    // public String deleteProduct(@PathVariable(name = "id") int
    // id,HttpServletRequest request) {
    // HttpSession session = request.getSession(true);
    // Object usernames = session.getAttribute("username");
    // if (usernames == null) {
    // return "redirect:/auth/login";
    // }
    // productServiceservice.deleteById(id);
    // return "redirect:/admin/product";
    // }

    // // @PostMapping("/product")
    // // public String searchProduct(Model model,HttpServletRequest request) {
    // // List<Product> dsSearch =new ArrayList<Product>();

    // // String search = request.getParameter("search").toLowerCase().trim();
    // // ds.forEach(e -> {
    // // if (e.getName().toLowerCase().trim().contains(search)) {
    // // dsSearch.add(e);
    // // }
    // // });
    // // model.addAttribute("products", dsSearch);
    // // return "admin/home";
    // // }

    // @PostMapping("/product/save")
    // public String saveProduct(HttpServletRequest request, HttpServletResponse
    // response) {
    // String name = request.getParameter("name");
    // String slug = request.getParameter("slug");
    // double price = Double.parseDouble(request.getParameter("price"));
    // String img = request.getParameter("img");
    // Set<String> imgList = new HashSet<String>();
    // imgList.add(img);
    // double sceen = Double.parseDouble(request.getParameter("screen"));
    // String chip = request.getParameter("chip");
    // int ram = Integer.parseInt(request.getParameter("ram"));
    // int rom = Integer.parseInt(request.getParameter("rom"));
    // String bestSeller = request.getParameter("bestSeller");
    // String type = request.getParameter("type");

    // Product product = new Product();
    // product.setName(name);
    // product.setSlug(slug);
    // product.setPrice(price);
    // product.setImg(imgList);
    // product.setScreen(sceen);
    // product.setChip(chip);
    // product.setRam(ram);
    // product.setRom(rom);
    // product.setBestSeller(bestSeller);
    // product.setType(type);

    // productServiceservice.save(product);

    // System.out.println(ds.size());

    // return "redirect:/admin/product";
    // }
}
