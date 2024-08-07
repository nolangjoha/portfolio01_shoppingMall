package com.smilemall.basic.kakaopay;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smilemall.basic.cart.CartProductVo;
import com.smilemall.basic.cart.CartService;
import com.smilemall.basic.member.MemberVO;
import com.smilemall.basic.order.OrderService;
import com.smilemall.basic.order.OrderVo;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/kakao/*")
public class KakaopayController {

	private final KakaopayService kakaopayService;
	private final CartService cartService;
	private final OrderService orderService;
	
	private OrderVo vo;
	private String mbsp_id;
	
	
	// [결제준비 요청]
	@ResponseBody
	@GetMapping("/kakaoPay")
	public ReadyResponse kakaoPay(OrderVo vo, HttpSession session) {
		
		log.info("주문자정보:" + vo);
		
		//아이디확보
		String mbsp_id = ((MemberVO) session.getAttribute("login_status")).getMbsp_id();
		this.mbsp_id = mbsp_id;
		
			
		//1) 장바구니 전체물품 구매시 // 장바구니 상품목록
		List<CartProductVo> cart_list = cartService.cart_list(mbsp_id);
		
		//상품정보
		String itemName = "";
		int quantity = 0;
		int totalAmount = 0;
		int taxFreeAmount = 0;
		int vatAmount = 0;
		
		for(int i=0; i<cart_list.size(); i++) {
			itemName += cart_list.get(i).getPro_name() + "/";
			quantity += cart_list.get(i).getCart_amount();
			totalAmount += cart_list.get(i).getPro_price() * cart_list.get(i).getCart_amount();			
		}
		
		
		
		String partnerOrderId = mbsp_id;
		String partnerUserId = mbsp_id;

		// 결제 준비 요청
		ReadyResponse readyResponse = kakaopayService.ready(partnerOrderId, partnerUserId, itemName, quantity, totalAmount, taxFreeAmount, vatAmount);
		
		log.info("응답데이터:" + readyResponse);
		
		//주문정보
		this.vo = vo;
		
		return readyResponse; // 결제 준비 응답
	}
	
	
	//[결제승인]
	@GetMapping("/approval")
	public void approval(String pg_token) {
		
		log.info("pg_token값: "+ pg_token);
		
		//결제 승인요청
		String approveResponse = kakaopayService.approve(pg_token); 
		log.info("최종결과:" + approveResponse);
		
		//승인요청응답에 aid(요청고유번호)가 포함되어 있다면
		if(approveResponse.contains("aid")) {
			//주문정보, 아이디, 결제방법, 상태, 결제정보를 저장하겠다.
			orderService.order_process(vo, mbsp_id, "kakaopay", "완료", " kakaopay");
		}
		
	}
	
	
	//[결제취소]
	@GetMapping("/cancel")
	public void cancel() {
		
	}
	
	
	//[결제실패]
	@GetMapping("/fail")
	public void fail() {
		
	}

}
