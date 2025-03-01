package com.smilemall.basic.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smilemall.basic.cart.CartProductVo;
import com.smilemall.basic.cart.CartService;
import com.smilemall.basic.cart.CartVo;
import com.smilemall.basic.member.MemberService;
import com.smilemall.basic.member.MemberVo;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/order/*")
public class OrderController {

	private final OrderService orderService;
	private final CartService cartService;
	private final MemberService memberService;
	
	
	// [장바구니 > 주문서작성 페이지]
	@GetMapping("/orderinfo")
	public String orderinfo(CartProductVo vo, Model model, HttpSession session,@ModelAttribute("type") String type) throws Exception {
			log.info("주문하기 상품정보:" + vo);
			log.info("결제타입:" + type);
			
		// <상품리스트(pro_list.html), 상품상세(pro_detail.html)에서 구매하기 버튼 클릭시>
		if(type.equals("direct")) {
			model.addAttribute("cart_list", vo);
			
			int total_price = vo.getCart_amount() * vo.getPro_price();
			model.addAttribute("total_price", total_price);
			
		} else if(type.equals("cart")) {
			// <장바구니(cart_list.html)에서 구매하기 버튼 클릭시>
			// 아이디 확보
			String mbsp_id = ((MemberVo) session.getAttribute("login_status")).getMbsp_id();
			
			
			//장바구니 상품목록
			List<CartProductVo> cart_list = cartService.cart_list(mbsp_id);
			 
			//이미지 폴더 구분자 변환
			cart_list.forEach(d_vo -> d_vo.setPro_up_folder(d_vo.getPro_up_folder().replace("\\", "/")));
			model.addAttribute("cart_list", cart_list);
			
			
			//상품가격 합계
			int total_price = 0;
			for(int i=0; i < cart_list.size(); i++) {
				total_price += (cart_list.get(i).getPro_price() * cart_list.get(i).getCart_amount());
			}
			model.addAttribute("total_price", total_price);
		}

		return "/order/orderinfo";
	}

	
	// [주문자 회원정보와 동일]
	@GetMapping("/ordersame")
	public ResponseEntity<MemberVo> ordersame(HttpSession session) throws Exception {
		
		ResponseEntity<MemberVo> entity = null;
		// 아이디 확보
		String mbsp_id = ((MemberVo) session.getAttribute("login_status")).getMbsp_id();
		
		entity = new ResponseEntity<MemberVo> (memberService.login(mbsp_id), HttpStatus.OK);
		
		return entity;
	}
	
	
	// [무통장입금] 
	@PostMapping("/ordersasve")
	public String ordersave(OrderVo vo, String pay_nobank, String pay_nobank_user, HttpSession session, 
			CartProductVo cp_vo, @ModelAttribute("type") String type) throws Exception {
		
		log.info("무통장입금 주문정보:" + vo);
		log.info("입금은행:" + pay_nobank);
		log.info("예금주:" + pay_nobank_user);
		log.info("무통장 입금 주문정보:" + cp_vo);
		log.info("바로구매/장바구니구매:" + type);
		
		// 아이디 확보
		String mbsp_id = ((MemberVo) session.getAttribute("login_status")).getMbsp_id();
		vo.setMbsp_id(mbsp_id);
		
		// 결제정보 : 은행주 + 예금주
		String payinfo = pay_nobank_user + "/" + pay_nobank;
		
		//결제정보 :DB insert
		orderService.order_process_direct(vo, mbsp_id, "무통장입금", "미납", payinfo, cp_vo, type);
		
		return "redirect:/order/ordercomplete";		
		
	}
	
	// [무통장입금 주문완료 페이지]
	@GetMapping("/ordercomplete")
	public void ordercomplete() {
		
	}
	
	
	
	
	
}
