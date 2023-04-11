package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.mdoel.Student;
import com.example.services.StudentService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class StudenController {

	@Autowired
	StudentService service;

	@GetMapping("/student/form")
	public String showForm() {
		return "studenform";
	}

	@PostMapping("/student/save")
	public String saveForm(HttpServletRequest request, Model model) {
		String ids = request.getParameter("id");
		String names = request.getParameter("name");
		String emails = request.getParameter("email");

		Student sm = new Student();
		sm.setId(Integer.parseInt(ids));
		sm.setName(names);
		sm.setEmail(emails);

		service.save(sm);

		return "redirect:/student/show";
	}

	@GetMapping("/student/show")
	public String showAll(ModelMap model) {
		model.addAttribute("students", service.findAll());
		return "studentshow";
	}

	@RequestMapping("/student/delete/{id}")
	public String delete(ModelMap model,@PathVariable(name = "id")Integer id) {

		service.deleteById(id);
		return "redirect:/student/show";
	}

}
