package com.smilemall.basic;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.smilemall.basic.admin.category.AdminCategoryService;
import com.smilemall.basic.admin.category.CategoryVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

	private final AdminCategoryService adminCategoryService;
	
	@GetMapping("/")
	public String index(Model model) {
		log.info("기본주소");
		
		// [1차 카테고리 불러오기]
		List<CategoryVo> cate_list = adminCategoryService.getFirstCategoryList();				
		model.addAttribute("user_cate_list", cate_list); 
		
		return "index";
	}
	
	
	
}
