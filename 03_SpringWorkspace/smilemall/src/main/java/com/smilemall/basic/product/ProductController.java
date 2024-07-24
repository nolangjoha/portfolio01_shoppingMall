package com.smilemall.basic.product;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smilemall.basic.admin.Product.ProductVo;
import com.smilemall.basic.common.constants.Constants;
import com.smilemall.basic.common.dto.Criteria;
import com.smilemall.basic.common.dto.PageDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/product/*")
public class ProductController {
	
	private final ProductService productService;
	
	
	@GetMapping("/pro_list")
	public void pro_list(@ModelAttribute("cat_code") int cat_code, @ModelAttribute("cat_name") String cat_name, Model model, Criteria cri) throws Exception {
		
		cri.setAmount(Constants.PRODUCT_LIST_AMOUNT);
		
		List<ProductVo> pro_list = productService.pro_list(cat_code, cri);
		
		int totalCount = productService.getCountProductByCategory(cat_code);
		
		model.addAttribute("pro_list", pro_list);
		model.addAttribute("pageMaker", new PageDTO(cri, totalCount));
	}
	
	
	
}
