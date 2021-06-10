package com.kurly.api;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kurly.service.ProductImageService;
import com.kurly.service.ProductService;
import com.kurly.vo.ProductVO;
import com.kurly.vo.SearchVO;

@RestController
public class ProductAPIController {
	@Autowired
	ProductService service;
	@Autowired
	ProductImageService imageService;
	
	@PutMapping("/product")
	public Map<String, String> putProduct(@RequestBody ProductVO vo) {
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		
		service.insertProduct(vo);
		// System.out.println(vo.getMkp_seq());
		resultMap.put("status", "success");
		resultMap.put("prod_seq", vo.getMkp_seq().toString());
		
		return resultMap;
	}
	
	@PutMapping("/product_img/{seq}")
	public Map<String, String> putProductImage(
			@RequestPart MultipartFile file,
			@PathVariable Integer seq,
			@RequestParam String brand,
			@RequestParam String name
		) throws Exception {
		
		if(file.getOriginalFilename() == "") {
			return null;
		}
		
		ProductVO vo = new ProductVO();
		vo.setMkp_name(name);
		vo.setMkp_seq(seq);
		vo.setBrand_name(brand);
		
		return imageService.insertProductImage(file, vo);
	}
	
	@GetMapping("/product_img/{fileName}")
	public ResponseEntity<Resource> getProductImage(@PathVariable String fileName, HttpServletRequest request) throws Exception {
		Resource resource = imageService.getProductImage(fileName);
		
		String contentType = null;
		contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		if(contentType == null) {
			contentType = "application/octet-stream";
		}
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=\""+resource.getFilename()+"\"")
				.body(resource);
	}
	
	@DeleteMapping("/product/{seq}")
	public Map<String, String> deleteProduct(@PathVariable Integer seq) {
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		
		resultMap.put("status", "success");
		resultMap.put("message", "삭제되었습니다.");
		
		service.deleteProduct(seq);
		
		return resultMap;
	}
	
	@GetMapping("/product/{seq}")
	public Map<String, Object> getProduct(@PathVariable Integer seq) {
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		ProductVO vo = service.selectProductBySeq(seq);
		if(vo != null) {
			resultMap.put("status", "success");
			resultMap.put("product", vo);
		}
		else {
			resultMap.put("status", "failed");
			resultMap.put("reason", seq+"번 데이터는 존재하지 않습니다.");
		}
		return resultMap;
	}
	
	@PatchMapping("/product")
	public Map<String, String> patchProduct(@RequestBody ProductVO vo) {
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		
		service.updateProduct(vo);
		
		resultMap.put("status", "success");
		resultMap.put("message", "수정되었습니다.");
		
		return resultMap;
	}
	
	@PostMapping("/product_list")
	public List<ProductVO> getProductList(@RequestBody @Nullable SearchVO vo) {
		
		if(vo != null){
			vo.setKeyword("%"+vo.getKeyword()+"%");
		}
		return service.selectProdRecommandPopupList(vo);
	}
	
	@PostMapping("/product_md_list")
	public List<ProductVO> postMDProductList(@RequestBody @Nullable SearchVO vo) {
		
		if(vo != null){
			vo.setKeyword("%"+vo.getKeyword()+"%");
		}
		return service.selectProdMDRecommandPopupList(vo);
	}
	
	@PostMapping("/product_special_list")
	public List<ProductVO> getSpecialProductList(@RequestBody @Nullable SearchVO vo) {
		
		if(vo != null){
			vo.setKeyword("%"+vo.getKeyword()+"%");
		}
		return service.selectProdSpecialPopupList(vo);
	}
	
	@PostMapping("/product_afford_list")
	public List<ProductVO> getAffordProductList(@RequestBody @Nullable SearchVO vo) {
		
		if(vo != null){
			vo.setKeyword("%"+vo.getKeyword()+"%");
		}
		return service.selectProdAffordPopupList(vo);
	}
	
	@PutMapping("/recommand")
	public Map<String, String> putRecommand(@RequestBody List<Integer> seq) {
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		
		seq.forEach(n -> {
			service.insertRecommandProduct(n);
		});
		
		resultMap.put("status", "success");
		return resultMap;
	}
	
	@PutMapping("/md_recommand")
	public Map<String, String> putMDRecommand(@RequestBody List<Integer> seq) {
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		
		seq.forEach(n -> {
			service.insertMDRecommandProduct(n);
		});
		
		resultMap.put("status", "success");
		return resultMap;
	}
	
	@PutMapping("/special")
	public Map<String, String> putSpecial(@RequestBody List<Integer> seq) {
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		
		seq.forEach(n -> {
			service.insertSpecialProduct(n);
		});
		
		resultMap.put("status", "success");
		return resultMap;
	}
	
	@PutMapping("/afford")
	public Map<String, String> putAfford(@RequestBody List<Integer> seq) {
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		
		seq.forEach(n -> {
			service.insertAffordProduct(n);
		});
		
		resultMap.put("status", "success");
		return resultMap;
	}
	
	@DeleteMapping("/recommand/{seq}")
	public Map<String, String> deleteRecommandProduct(@PathVariable Integer seq) {
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		
		service.deleteRecommandProduct(seq);
		resultMap.put("status", "success");
		
		return resultMap;
	}
	
	@DeleteMapping("/md_recommand/{seq}")
	public Map<String, String> deleteMDRecommandProduct(@PathVariable Integer seq) {
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		
		service.deleteMDRecommandProduct(seq);
		resultMap.put("status", "success");
		
		return resultMap;
	}
	
	@DeleteMapping("/special/{seq}")
	public Map<String, String> deleteSpecialProduct(@PathVariable Integer seq) {
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		
		service.deleteSpecialProduct(seq);
		resultMap.put("status", "success");
		
		return resultMap;
	}
	
	@DeleteMapping("/afford/{seq}")
	public Map<String, String> deleteAffordProduct(@PathVariable Integer seq) {
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		
		service.deleteAffordProduct(seq);
		resultMap.put("status", "success");
		
		return resultMap;
	}
}


















