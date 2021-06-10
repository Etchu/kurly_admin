package com.kurly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kurly.service.ProductService;
import com.kurly.vo.ProductVO;
import com.kurly.vo.SimpleProductVO;

@Controller
public class ProductController {
	
	@Autowired
	ProductService service;
	
	@GetMapping("/products")
	public String getProducts(Model model) {
		model.addAttribute("menu_num", 0);
		List<ProductVO> productList = service.selectProducts(null);
		
		model.addAttribute("list", productList);
		
		return "/products/list";
	}
	
	@GetMapping("/recommand")
	public String getRecommand(Model model) {
		model.addAttribute("menu_num", 2);
		
		List<SimpleProductVO> recommandList = service.selectRecommandList();
		model.addAttribute("recommandList", recommandList);
		
		return "/products/recommand";
	}
	
	@GetMapping("/md_recommand")
	public String getMDRecommand(Model model) {
		model.addAttribute("menu_num", 3);
		
		List<SimpleProductVO> recommandList = service.selectMDRecommandList();
		model.addAttribute("recommandList", recommandList);
		
		return "/products/md_recommand";
	}
		
	@GetMapping("/afford")
	public String getAfford(Model model) {
		model.addAttribute("menu_num", 6);
		
		List<SimpleProductVO> affordList = service.selectAffordList();
		model.addAttribute("affordList", affordList);
		
		return "/products/afford";
	}
	
	@GetMapping("/special")
	public String getSpecial(Model model) {
		model.addAttribute("menu_num", 5);
		
		List<SimpleProductVO> specialList = service.selectSpecialList();
		model.addAttribute("specialList", specialList);
		
		return "/products/special";
	}
}

















