package com.kurly.api;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kurly.service.CategoryService;
import com.kurly.service.CompanyService;
import com.kurly.vo.CategoryVO;
import com.kurly.vo.CompanyVO;

@RestController
public class CompanyAPIController {
	@Autowired
	CompanyService service;
	
	@GetMapping("/api/insert_company")
	public Map<String, String> getInsertCompany(@RequestParam String name) {
		Map<String, String> resultMap = new LinkedHashMap<String, String>();	
		
		boolean result = service.insertCompany(name);
		if(result) {
			resultMap.put("status", "success");
			resultMap.put("message", "추가되었습니다.");
		}
		else {
			resultMap.put("status", "failed");
			resultMap.put("message", "["+name+"]은/는 이미 존재합니다.");
		}
		
		return resultMap;
	}
	
	@DeleteMapping("/company")
	public Map<String, String> deleteCompany(@RequestParam Integer seq) {
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		service.deleteCompany(seq);
		resultMap.put("status", "success");
		resultMap.put("message", "삭제되었습니다.");
		return resultMap;
	}
	
	@PatchMapping("/company/{seq}")
	public Map<String, String> patchCompany(@PathVariable Integer seq, @RequestParam String name) {
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		CompanyVO vo = new CompanyVO();
		vo.setMkb_seq(seq);
		vo.setMkb_name(name);
		boolean updateStatus = service.updateCompany(vo);
		
		if(updateStatus) {
			resultMap.put("status", "success");
			resultMap.put("message", "변경되었습니다.");
		}
		else {
			resultMap.put("status", "failed");
			resultMap.put("message", "카테고리 이름이 중복됩니다.");
		}
		
		return resultMap;
	}
	@GetMapping("/company_list")
	public List<CompanyVO> getCompany() {
		return service.selectCompanies();
	}
}







