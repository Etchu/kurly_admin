package com.kurly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kurly.vo.ProductVO;
import com.kurly.vo.SearchVO;
import com.kurly.vo.SimpleProductVO;

@Mapper
public interface ProductMapper {
	public void insertProduct(ProductVO vo);
	public List<ProductVO> selectProducts(SearchVO vo);
	public void deleteProduct(Integer seq);
	public ProductVO selectProductBySeq(Integer seq);
	public void updateProduct(ProductVO vo);
	public void insertRecommandProduct(Integer seq);
	public void insertMDRecommandProduct(Integer seq);
	public List<ProductVO> selectProdRecommandPopupList(SearchVO vo);
	public List<ProductVO> selectProdMDRecommandPopupList(SearchVO vo);
	public List<SimpleProductVO> selectRecommandList();
	public List<SimpleProductVO> selectMDRecommandList();
	public void deleteRecommandProduct(Integer seq);
	public void deleteMDRecommandProduct(Integer seq);
	
	public void insertAffordProduct(Integer seq);
	public List<ProductVO> selectProdAffordPopupList(SearchVO vo);
	public List<SimpleProductVO> selectAffordList();
	public void deleteAffordProduct(Integer seq);
	
	public void insertSpecialProduct(Integer seq);
	public List<ProductVO> selectProdSpecialPopupList(SearchVO vo);
	public List<SimpleProductVO> selectSpecialList();
	public void deleteSpecialProduct(Integer seq);
}







