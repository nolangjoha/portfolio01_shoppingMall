package com.smilemall.basic.admin.Product;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smilemall.basic.admin.category.AdminCategoryService;
import com.smilemall.basic.admin.category.CategoryVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/product/*")
public class AdminProductController {

	private final AdminProductService adminProductService;
	
	private final AdminCategoryService adminCategoryService;
	
	
	//[상픔등록폼]
	@GetMapping("pro_insert")
	public void pro_insertForm(Model model) {
		List<CategoryVo> cate_list = adminCategoryService.getFirstCategoryList();
		model.addAttribute("cate_list", cate_list);
	}
	
	
}

