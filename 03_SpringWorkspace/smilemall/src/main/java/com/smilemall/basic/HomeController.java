package com.smilemall.basic;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.smilemall.basic.admin.Product.AdminProductService;
import com.smilemall.basic.admin.Product.ProductVo;
import com.smilemall.basic.admin.carousel.AdminCarouselService;
import com.smilemall.basic.admin.carousel.CarouselVo;
import com.smilemall.basic.admin.category.AdminCategoryService;
import com.smilemall.basic.admin.category.CategoryVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

	private final AdminCategoryService adminCategoryService;
	private final AdminCarouselService adminCarouselService;
	private final AdminProductService adminProductService;
	 
	@GetMapping("/")
	public String main_home(Model model) {
		log.info("기본주소");
		
		// [1차 카테고리 불러오기]
		List<CategoryVo> cate_list = adminCategoryService.getFirstCategoryList();				
		model.addAttribute("user_cate_list", cate_list); 
		
		// [슬라이드 배너 불러오기]
		List<CarouselVo> carousel_post_list = adminCarouselService.carousel_post_list();
		model.addAttribute("carousel_post_list", carousel_post_list);
		
		// [신상품 목록 불러오기]
		List<ProductVo> new_pro_list = adminProductService.new_pro_list();
		//log.info("신제품" + new_pro_list);
		model.addAttribute("new_pro_list", new_pro_list);
		
		// [베스트 셀러 상품 불러오기]
		List<Map<String,Object>> best_item_list = adminProductService.best_item_list();
		model.addAttribute("best_item_list", best_item_list);
		//log.info("베스트셀러" + best_item_list);
		
//		for (Map<String, Object> item : best_item_list) {
//		    log.info("상품명: " + item.get("PRO_NAME"));
//		    log.info("가격: " + item.get("PRO_PRICE"));
//		}
		
		return "main_home";
	}
	
	
	
}
