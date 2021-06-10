package com.kurly.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.kurly.mapper.ProductImageMapper;
import com.kurly.vo.ProductImageVO;
import com.kurly.vo.ProductVO;

@Service
public class ProductImageService {
	@Autowired
	ProductImageMapper mapper;
	public Map<String, String> insertProductImage(MultipartFile file, ProductVO vo) throws Exception {
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		Path prodImageLocation = Paths.get("/kurly/product_imgs");
		ProductImageVO imgVO = new ProductImageVO();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		//String regex = "!\\\"#[$]%&\\\\(\\\\)\\\\{\\\\}@`[*]:[+];-.<>,\\\\^~|'\\\\[\\\\]";
		// ! \ # $ % & ( ) { } @ * + - < > | [ ] ' "
		//fileName = vo.getBrand_name().replaceAll(regex, "_")+"_"+vo.getMkp_name()+"_"+fileName.replaceAll(regex, "_");
		fileName = vo.getBrand_name()+"_"+vo.getMkp_name()+"_"+fileName;
		imgVO.setMkpi_uri(fileName.substring(0, fileName.indexOf(".")));
		
		Calendar c = Calendar.getInstance();
		String currentDate = ""+
				c.get(Calendar.YEAR)+(c.get(Calendar.MONTH)+1)+c.get(Calendar.DATE)+
				c.get(Calendar.HOUR_OF_DAY)+c.get(Calendar.MINUTE);
		fileName = currentDate+"_"+fileName;
		imgVO.setMkpi_real_path(fileName);
		imgVO.setMkpi_product_seq(vo.getMkp_seq());
		imgVO.setMkpi_size(file.getSize());
		imgVO.setMkpi_name(file.getOriginalFilename());
		Path targetLocation = prodImageLocation.resolve(fileName);
		Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
		mapper.insertProductImage(imgVO);
		return resultMap;
	}
	
	public Resource getProductImage(String fileName) throws Exception {
		ProductImageVO vo = mapper.selectProductImage(fileName);
		Path prodImageLocation = Paths.get("/kurly/product_imgs");
		Path prodImagePath = prodImageLocation.resolve(vo.getMkpi_real_path()).normalize();
		Resource resource = new UrlResource(prodImagePath.toUri());
		return resource;
	}
}






