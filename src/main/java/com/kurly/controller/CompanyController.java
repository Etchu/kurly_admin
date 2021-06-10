package com.kurly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kurly.service.CompanyService;
import com.kurly.vo.CompanyVO;

@Controller
public class CompanyController {
	
	@Autowired
	CompanyService service;
	
	@GetMapping("/company")
	public String getCompany(Model model) {
		model.addAttribute("menu_num", 4);
		
		List<CompanyVO> list = service.selectCompanies();
		model.addAttribute("list", list);
		
		return "/company/company_list";
	}
}














