package com.example.controllers;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.mdoel.SanPham;
import com.example.services.SanPhamService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AdminController {
	@Autowired
	SanPhamService service;

	@GetMapping("/admin")
	public String getAdmin() {
		return "Admin";
	}

	@GetMapping("/admin/createCourse")
	public String getCreateCourse() {
		return "Admin";
	}

	@PostMapping("/admin/saveCourse")
	public String saveCourse(HttpServletRequest req) {
		Random rand = new Random();
		int id = rand.nextInt(100) + 1;
		String tenSP = req.getParameter("tenSanpham");
		String img = req.getParameter("img");
		String chip = req.getParameter("chip");
		double manHinh = Double.parseDouble(req.getParameter("manHinh"));
		int rom = Integer.parseInt(req.getParameter("rom"));
		int ram = Integer.parseInt(req.getParameter("ram"));
		double gia = Double.parseDouble(req.getParameter("gia"));
		String loaiSanpham = req.getParameter("loaiSanpham");
		String bestSeller = req.getParameter("bestSeller");
		String slug = req.getParameter("slug");

		Set<String> dsimg = new HashSet<>();
		dsimg.add(img);
		
		for (SanPham sp : service.findAll()) {
			if(sp.getId() == id) {
				id = rand.nextInt(100) + 1;
			}
		}

		SanPham sanPham = new SanPham(id, tenSP, dsimg, chip, manHinh, rom, ram, gia, slug, loaiSanpham, bestSeller);
		service.save(sanPham);

		return "redirect:/admin";
	}
}
