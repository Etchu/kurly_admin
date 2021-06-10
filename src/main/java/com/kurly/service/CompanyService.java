package com.kurly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kurly.mapper.CompanyMapper;
import com.kurly.vo.CompanyVO;

@Service
public class CompanyService {
	@Autowired
	CompanyMapper mapper;
	public boolean insertCompany(String name) {
		Integer result = mapper.selectCompanyByName(name);
		if(result == 1) {
			return false;
		}
		mapper.insertCompany(name);
		
		return true;
	}
	public List<CompanyVO> selectCompanies(){
		return mapper.selectCompanies();
	}
	public void deleteCompany(Integer seq) {
		mapper.deleteCompany(seq);
	}
	public boolean updateCompany(CompanyVO vo) {
		if(vo.getMkb_name() == null || vo.getMkb_name() == "") {
			return false;
		}
		Integer n = mapper.selectCompanyByName(vo.getMkb_name());
		if(n == 1) {
			return false;
		}
		mapper.updateCompany(vo);
		return true;
	}
}
