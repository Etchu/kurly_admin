package com.kurly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kurly.service.MemberService;
import com.kurly.vo.MemberVO;

@Controller
public class MemberController {

	@Autowired
	MemberService service;
	
	@GetMapping("member")
	public String getMember(Model model, @RequestParam @Nullable Integer offset) {
		if(offset == null) {
			offset = 0;
		}
		List<MemberVO> memberList = service.selectMembers(offset);
		
		model.addAttribute("memberList", memberList);
		model.addAttribute("menu_num", 7);
		
		return "member/member_list";
	}
}
