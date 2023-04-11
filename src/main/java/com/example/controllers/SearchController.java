package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class SearchController {

	@PostMapping("search")
	@ResponseBody
	public String postSearch(HttpServletRequest req) {
		String key = req.getParameter("key");
		return "key tìm kiếm là: "+key;
	}
}
