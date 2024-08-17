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
	private CartProductVo cp_vo;
	private String type;
	
	
	// [결제준비 요청]
	@ResponseBody
	@GetMapping("/kakaoPay")
	public ReadyResponse kakaoPay(CartProductVo cp_vo, OrderVo vo, HttpSession session, String type) {
		
		log.info("type값:"+ type);
		
		// 1) 바로구매 했을 경우
		if(type.equals("direct")) {
		
			// 아이디확보 
			String mbsp_id = ((MemberVO) session.getAttribute("login_status")).getMbsp_id();
			this.mbsp_id = mbsp_id;
			
			log.info("주문자정보:" + vo);
			log.info("상품정보:" + cp_vo);
			
			// 구매상품 번호
			int pro_num = cp_vo.getPro_num();  // pro_num
			
			//구매할 상품 정보
			CartProductVo pro_vo = orderService.directOrderProduct(pro_num);  // pro_name, pro_price
			pro_vo.setCart_amount(cp_vo.getCart_amount());  // 구매상품 수량 cart_amount
			
			log.info("상품정보2:" + pro_vo);
			
			// 상품 가격(배송비 제외)
			int order_price = pro_vo.getCart_amount() * pro_vo.getPro_price();
			
			log.info("상품가격:" + order_price);
			
			String partnerOrderId = mbsp_id;
			String partnerUserId = mbsp_id;
			String itemName = pro_vo.getPro_name();
			int quantity = 0;
			// 결제가격 
			int totalAmount;
				// 결제가격 배송비 포함여부
				if(order_price < 100000) {
					totalAmount = order_price + 3000;
				} else {
					totalAmount = order_price;
				}
			log.info("배송비 포함 상품가격:" + totalAmount);
				
			int taxFreeAmount = 0;
			int vatAmount = 0;
			
			// 결제 준비 요청
			ReadyResponse readyResponse = kakaopayService.ready(partnerOrderId, partnerUserId, itemName, quantity, totalAmount, taxFreeAmount, vatAmount);
			
			log.info("응답데이터:" + readyResponse);
			
			//주문정보
			this.vo = vo;
			
			log.info("바로구매vo"+ vo);
			//바로구매, 장바구니구매
			this.type = type;
			
			this.cp_vo = cp_vo;
			
			log.info("장바구니 구매vo"+ vo);
			log.info("요청전 마지막type 확인:" + type);
			
			return readyResponse; // 결제 준비 응답
			
		} else { 
			//2) 장바구니에서 전체 구매를 했을 경우
			//아이디확보
			String mbsp_id = ((MemberVO) session.getAttribute("login_status")).getMbsp_id();
			this.mbsp_id = mbsp_id;
					
			log.info("장바구니 구매");
			
			//1) 장바구니 전체물품 구매시 // 장바구니 상품목록
			List<CartProductVo> cart_list = cartService.cart_list(mbsp_id);
			
			log.info("장바구니 카카오구매" + cart_list); //pro_price=60000
			
			//상품정보
			String partnerOrderId = mbsp_id;
			String partnerUserId = mbsp_id;
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
			
			log.info("카카오페이 장바구니 상품각격 총액 if문 전:" + totalAmount);
			
			if(totalAmount < 100000 ) {
				totalAmount += 3000;
			} 
				
			log.info("카카오페이 장바구니 상품각격 총액 if문 후:" + totalAmount);
			
			// 결제 준비 요청
			ReadyResponse readyResponse = kakaopayService.ready(partnerOrderId, partnerUserId, itemName, quantity, totalAmount, taxFreeAmount, vatAmount);
			
			log.info("응답데이터:" + readyResponse);
			
			//주문정보
			this.vo = vo;
	
			
			//바로구매, 장바구니구매
			this.type = type;
			
			this.cp_vo = cp_vo;
			
			log.info("장바구니 구매vo"+ vo);
			log.info("요청전 마지막type 확인:" + type);
			
			return readyResponse; // 결제 준비 응답
		}
		
	}
	
	
	
	//[결제승인]
	@GetMapping("/approval")
	public void approval(String pg_token) {
		
		log.info("pg_token값: "+ pg_token);
		
		log.info("승인시 type: " + type);
		log.info("승인시 cp_vo:" + cp_vo);
		
		//결제 승인요청
		String approveResponse = kakaopayService.approve(pg_token); 
		log.info("최종결과:" + approveResponse);
		
		//승인요청응답에 aid(요청고유번호)가 포함되어 있다면
		if(approveResponse.contains("aid")) {
			//주문정보, 아이디, 결제방법, 상태, 결제정보를 저장하겠다.
			//orderService.order_process(vo, mbsp_id, "kakaopay", "완료", " kakaopay");
			orderService.order_process_direct(vo, mbsp_id, "kakaopay", "결제완료" ,"kakaopay", cp_vo, type);				
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
