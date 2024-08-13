package com.smilemall.basic.admin.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smilemall.basic.common.constants.Constants;
import com.smilemall.basic.common.dto.Criteria;
import com.smilemall.basic.common.dto.PageDTO;
import com.smilemall.basic.common.util.FileManagerUtils;
import com.smilemall.basic.order.OrderVo;
import com.smilemall.basic.payinfo.PayInfoService;
import com.smilemall.basic.payinfo.PayInfoVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/order/*")
public class AdminOrderController {

	// 상품이미지 업로드 경로
	@Value("${file.product.image.dir}")
	private String uploadPath;
		
	private final AdminOrderService adminOrderService;
	private final PayInfoService payInfoService;
	
	
	// [전체 주문목록]
	@GetMapping("/order_list")
	public void order_list(Criteria cri, Model model) throws Exception {
		
		//주문목록 출력 개수
		cri.setAmount(Constants.AMIN_ORDER_LIST_AMOUNT);
		
		//주문목록
		List<OrderVo> order_list = adminOrderService.ord_list(cri);
		
		//총 주문데이터
		int totalCount = adminOrderService.getTotalCount(cri);

		model.addAttribute("order_list", order_list);
		model.addAttribute("pageMaker", new PageDTO(cri, totalCount) );
		
	}
	
	// [주문상세정보]
	@GetMapping("/order_detail_info")
	public ResponseEntity<Map<String, Object>> order_detail_info(Long ord_code,Model model) throws Exception {
		
		ResponseEntity<Map<String, Object>> entity = null;
		
		Map<String, Object> map = new HashMap<>();
		
		// [주문자정보(수령인:order_tbl)
		OrderVo vo = adminOrderService.order_info(ord_code);
		map.put("ord_basic", vo);
		
		// [주문자상세정보(상품:ordetail_tbl)]
		List<OrderDetailInfoVo> ord_product_list = adminOrderService.order_detail_info(ord_code);
		ord_product_list.forEach(ord_pro -> {
			ord_pro.setPro_up_folder(ord_pro.getPro_up_folder().replace("\\", "/"));
		});
		map.put("ord_pro_list",ord_product_list);
		
		// [상품가격 총합(배송비제외)]
		int totalProductPriceByOrder = adminOrderService.calculateTotalAmount(ord_code);
		map.put("totalProductPriceByOrder", totalProductPriceByOrder);
		
		log.info("상품합계:" +  totalProductPriceByOrder);
		
		// [결제정보]
		PayInfoVo p_vo = payInfoService.ord_pay_info(ord_code);
		map.put("payinfo", p_vo);
		
		entity = new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
		
		return entity;
		
	}
	
	
	
	
	
	// <상품리스트에서 사용할 이미지 보여주기>
	@GetMapping("/image_display")
	public ResponseEntity<byte[]> image_display(String dateFolderName, String fileName) throws Exception {
		
		return FileManagerUtils.getFile(uploadPath + dateFolderName, fileName);
	}

	
}
