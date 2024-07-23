package com.smilemall.basic.product;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smilemall.basic.admin.Product.ProductVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/product/*")
public class ProductController {
	
	private final ProductService productService;
	
	
	@GetMapping("/pro_list")
	public void pro_list(int cat_code, @ModelAttribute("cat_name") String cat_name, Model model) throws Exception {
		
		List<ProductVo> pro_list = productService.pro_list(cat_code);
		
		model.addAttribute("pro_list", pro_list);
	}
	
	
	
}
