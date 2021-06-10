package com.kurly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kurly.vo.CategoryVO;

@Mapper
public interface CategoryMapper {
	public void insertCategory(String name);
	public Integer selectCategoryByName(String name);
	public List<CategoryVO> selectCategories();
	public void deleteCategory(Integer seq);
	public void updateCategory(CategoryVO vo);
}

















