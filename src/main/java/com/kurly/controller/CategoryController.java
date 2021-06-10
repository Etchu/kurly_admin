package com.kurly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kurly.service.CategoryService;
import com.kurly.vo.CategoryVO;

@Controller
public class CategoryController {
	@Autowired
	CategoryService service;
	
	@GetMapping("/category")
	public String getCategory(Model model) {
		model.addAttribute("menu_num", 1);
		
		List<CategoryVO> list = service.selectCategories();
		model.addAttribute("list", list);
		
		return "/category/list";
	}
}
