package com.kurly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kurly.vo.CompanyVO;

@Mapper
public interface CompanyMapper {
	public void insertCompany(String name);
	public Integer selectCompanyByName(String name);
	public List<CompanyVO> selectCompanies();
	public void deleteCompany(Integer seq);
	public void updateCompany(CompanyVO vo);
}
