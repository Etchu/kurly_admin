package com.kurly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kurly.mapper.CategoryMapper;
import com.kurly.vo.CategoryVO;

@Service
public class CategoryService {
	@Autowired
	CategoryMapper mapper;
	public boolean insertCategory(String name) {
		Integer result = mapper.selectCategoryByName(name);
		if(result == 1) {
			return false;
		}
		mapper.insertCategory(name);
		
		return true;
	}
	
	public List<CategoryVO> selectCategories(){
		return mapper.selectCategories();
	}
	
	public void deleteCategory(Integer seq) {
		mapper.deleteCategory(seq);
	}
	
	public boolean updateCategory(CategoryVO vo) {
		if(vo.getMkpc_name() == null || vo.getMkpc_name() == "") {
			return false;
		}
		Integer n = mapper.selectCategoryByName(vo.getMkpc_name());
		if(n == 1) {
			return false;
		}
		mapper.updateCategory(vo);
		return true;
	}
}











