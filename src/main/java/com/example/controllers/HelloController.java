package com.example.controllers;

import javax.swing.SwingContainer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/dislay")
    public String dislay(HttpServletRequest request) {
        String name = request.getParameter("name");
        request.setAttribute("name", name);
        return "dislay";
    }
    
}
