package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.mdoel.FillterProduct;
import com.example.mdoel.SanPham;
import com.example.services.SanPhamService;

@Controller
public class HomeController {

	@Autowired
	SanPhamService service;
	
	@RequestMapping(value = "/trangchu", method = RequestMethod.GET)
	public String getHomePages(Model model) {
		model.addAttribute("sanphamdienthoais", service.findByBestsellerProduct("dienthoai", "true"));
		model.addAttribute("sanphamtablets", service.findByBestsellerProduct("tablet", "true"));
		return "index";
	}

	@RequestMapping(value = "/dienthoai", method = RequestMethod.GET)
	public String getDienthoai(Model model) {
		model.addAttribute("sanphams", service.findByLoaiSanpham("dienthoai"));
		model.addAttribute("slDienthoai", service.findByLoaiSanpham("dienthoai").size());
		model.addAttribute("brank","Điện thoại");
		return "home";
	}
	@RequestMapping(value = "/tablet", method = RequestMethod.GET)
	public String getTablet(Model model) {
		model.addAttribute("sanphams", service.findByLoaiSanpham("tablet"));
		model.addAttribute("slDienthoai", service.findByLoaiSanpham("tablet").size());
		model.addAttribute("brank","Tablet");
		return "home";
	}

	@PostMapping("/api/san-pham/loc")
	public ResponseEntity<?> fillterProduct(@RequestBody FillterProduct req) {
		List<SanPham> result = new ArrayList<SanPham>();

		if (!req.getBrands().isEmpty() && !req.getPrices().isEmpty()) {
			for (String brand : req.getBrands()) {
				for (String price : req.getPrices()) {
					double minprice = Double.parseDouble(price.split("-")[0]);
					double maxprice = Double.parseDouble(price.split("-")[1]);
					result.addAll(service.findMutiProducts(req.getloaiSanpham(), brand, minprice, maxprice));
				}
			}
		}
		if (req.getPrices().isEmpty() && !req.getBrands().isEmpty()) {
			for (String e : req.getBrands()) {
				result.addAll(service.findBySlug(req.getloaiSanpham(),e));
			}
		}
		if (req.getBrands().isEmpty() && !req.getPrices().isEmpty()) {
			for (String e : req.getPrices()) {
				double minprice = Double.parseDouble(e.split("-")[0]);
				double maxprice = Double.parseDouble(e.split("-")[1]);
				result.addAll(service.findByPrice(req.getloaiSanpham(), minprice, maxprice));
			}
		}
		if(req.getBrands().isEmpty() && req.getPrices().isEmpty()) {
			result = service.findByLoaiSanpham(req.getloaiSanpham());
		}
		return ResponseEntity.ok(result);
	}
}
