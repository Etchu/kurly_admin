package com.kurly.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminMainController {
	
	@GetMapping("/")
	public String getMain() {
		return "/login";
	}
	
	@GetMapping("/logout")
	public String getLogout(HttpSession session) {
		session.setAttribute("user", null);
		session.invalidate();
		return "redirect:/";
	}
}











