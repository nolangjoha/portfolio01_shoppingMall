package com.smilemall.basic.admin.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smilemall.basic.common.constants.Constants;
import com.smilemall.basic.common.dto.Criteria;
import com.smilemall.basic.common.dto.PageDTO;
import com.smilemall.basic.order.OrderVo;

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
	
	@GetMapping("/order_list")
	public void order_list(Criteria cri, Model model) {
		
		//주문목록 출력 개수
		cri.setAmount(Constants.AMIN_ORDER_LIST_AMOUNT);
		
		//주문목록
		List<OrderVo> order_list = adminOrderService.ord_list(cri);
		
		//총 주문데이터
		int totalCount = adminOrderService.getTotalCount(cri);

		model.addAttribute("order_list", order_list);
		model.addAttribute("pageMaker", new PageDTO(cri, totalCount) );
		
	}
	
	
	
	
}
