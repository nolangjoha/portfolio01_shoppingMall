package com.smilemall.basic.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smilemall.basic.admin.Product.ProductVo;
import com.smilemall.basic.common.constants.Constants;
import com.smilemall.basic.common.dto.Criteria;
import com.smilemall.basic.common.dto.PageDTO;
import com.smilemall.basic.common.util.FileManagerUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/product/*")
public class ProductController {
	
	private final ProductService productService;
	
	// 상품이미지 업로드 경로
	@Value("${file.product.image.dir}")
	private String uploadPath;
	
	// CKEditor파일 업로드 경로
	@Value("${file.ckeditor}")
	private String upliadCkpath;
	
	
	// [상품리스트]
	@GetMapping("/pro_list")
	public void pro_list(@ModelAttribute("cat_code") int cat_code, @ModelAttribute("cat_name") String cat_name, Model model, Criteria cri) throws Exception {
		
		//출력 게시물 갯수
		cri.setAmount(Constants.PRODUCT_LIST_AMOUNT);
		
		//상품목록
		List<ProductVo> pro_list = productService.pro_list(cat_code, cri);
		
		//이미지 폴더 구분자 변환
		pro_list.forEach(vo -> {
			vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
		});
		
		//카테고리별 상품 총갯수
		int totalCount = productService.getCountProductByCategory(cat_code);
		
		
		model.addAttribute("pro_list", pro_list);
		model.addAttribute("pageMaker", new PageDTO(cri, totalCount));
	}
	
	// 상품 리스트에서 사용할 이미지
	@GetMapping("/image_display")
	public ResponseEntity<byte[]> image_display(String dateFolderName, String fileName) throws Exception {
		return FileManagerUtils.getFile(uploadPath + dateFolderName, fileName);
	}
	
	
}
