package com.kurly.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kurly.mapper.ProductImageMapper;
import com.kurly.mapper.ProductMapper;
import com.kurly.vo.ProductVO;
import com.kurly.vo.SearchVO;
import com.kurly.vo.SimpleProductVO;

@Service
public class ProductService {
	@Autowired
	ProductMapper mapper;
	@Autowired
	ProductImageMapper imageMapper;
	
	public void insertProduct(ProductVO vo) {
		mapper.insertProduct(vo);
	}
	
	public List<ProductVO> selectProducts(SearchVO vo) {
		List<ProductVO> list = mapper.selectProducts(vo);
		
		String prefix="/product_img/";
		list.forEach(item -> {
			 String uri = imageMapper.selectProductImageURI(item.getMkp_seq());
			 if(uri != null) {
				 item.setImage_uri(prefix+uri);
			 }
		});
		return list;
	}
	
	public void deleteProduct(Integer seq) {
		mapper.deleteProduct(seq);
	}
	
	public ProductVO selectProductBySeq(Integer seq) {
		String imageName = imageMapper.selectProductImageName(seq);
		ProductVO vo = mapper.selectProductBySeq(seq);
		vo.setImage_name(imageName);
		return vo;
	}
	
	public void updateProduct(ProductVO vo) {
		mapper.updateProduct(vo);
	}
	
	public void insertRecommandProduct(Integer seq) {
		mapper.insertRecommandProduct(seq);
	}
	
	public void insertMDRecommandProduct(Integer seq) {
		mapper.insertMDRecommandProduct(seq);
	}
	
	public List<ProductVO> selectProdRecommandPopupList(SearchVO vo){
		List<ProductVO> resultList = new ArrayList<ProductVO>();
		List<ProductVO> originList = new ArrayList<ProductVO>();
		originList = mapper.selectProdRecommandPopupList(vo);
		originList.forEach(item -> {
			// 추천에 들어있지 않은 product를 걸러내서 resultList에 추가
			if(item.getMkrp_seq() == null) {
				resultList.add(item);
			}
		});
		return resultList;
	}
	
	public List<ProductVO> selectProdMDRecommandPopupList(SearchVO vo){
		List<ProductVO> resultList = new ArrayList<ProductVO>();
		List<ProductVO> originList = new ArrayList<ProductVO>();
		originList = mapper.selectProdMDRecommandPopupList(vo);
		
		originList.forEach(item -> {
			// 추천에 들어있지 않은 product를 걸러내서 resultList에 추가
			if(item.getMkmrp_seq() == null) {
				resultList.add(item);
			}
		});
		return resultList;
	}
	
	public List<SimpleProductVO> selectRecommandList() {
		return mapper.selectRecommandList();
	}
	
	public List<SimpleProductVO> selectMDRecommandList() {
		return mapper.selectMDRecommandList();
	}
	
	public void deleteRecommandProduct(Integer seq){
		mapper.deleteRecommandProduct(seq);
	}
	
	public void deleteMDRecommandProduct(Integer seq){
		mapper.deleteMDRecommandProduct(seq);
	}
	
	public List<ProductVO> selectProdAffordPopupList(SearchVO vo){
		List<ProductVO> resultList = new ArrayList<ProductVO>();
		List<ProductVO> originList = new ArrayList<ProductVO>();
		originList = mapper.selectProdAffordPopupList(vo);
		originList.forEach(item -> {
			// 추천에 들어있지 않은 product를 걸러내서 resultList에 추가
			if(item.getMkrp_seq() == null) {
				resultList.add(item);
			}
		});
		return resultList;
	}
	
	public void insertAffordProduct(Integer seq) {
		mapper.insertAffordProduct(seq);
	}
	
	public List<SimpleProductVO> selectAffordList() {
		return mapper.selectAffordList();
	}
	
	public void deleteAffordProduct(Integer seq){
		mapper.deleteAffordProduct(seq);
	}
	
	public List<ProductVO> selectProdSpecialPopupList(SearchVO vo){
		List<ProductVO> resultList = new ArrayList<ProductVO>();
		List<ProductVO> originList = new ArrayList<ProductVO>();
		originList = mapper.selectProdSpecialPopupList(vo);
		originList.forEach(item -> {
			// 추천에 들어있지 않은 product를 걸러내서 resultList에 추가
			if(item.getMkrp_seq() == null) {
				resultList.add(item);
			}
		});
		return resultList;
	}
	
	public void insertSpecialProduct(Integer seq) {
		mapper.insertSpecialProduct(seq);
	}
	
	public List<SimpleProductVO> selectSpecialList() {
		return mapper.selectSpecialList();
	}
	
	public void deleteSpecialProduct(Integer seq){
		mapper.deleteSpecialProduct(seq);
	}
}










