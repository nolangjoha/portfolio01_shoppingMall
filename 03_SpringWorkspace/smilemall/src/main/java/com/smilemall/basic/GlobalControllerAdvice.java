package com.smilemall.basic;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.smilemall.basic.admin.category.AdminCategoryService;
import com.smilemall.basic.admin.category.CategoryVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice(basePackages = {"com.smilemall.basic.product", "com.smilemall.basic.cart"})
@RequiredArgsConstructor
public class GlobalControllerAdvice {

	private final AdminCategoryService adminCategoryService;
	
	@ModelAttribute
	public void comm_test(Model model) {
		log.info("Global 공통코드 실행");
		
		// [1차 카테고리 불러오기]
		List<CategoryVo> cate_list = adminCategoryService.getFirstCategoryList();				
		model.addAttribute("user_cate_list", cate_list); 
		
	}
	
}
