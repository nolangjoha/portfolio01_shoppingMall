package com.smilemall.basic.order;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smilemall.basic.cart.CartProductVo;
import com.smilemall.basic.cart.CartService;
import com.smilemall.basic.cart.CartVo;
import com.smilemall.basic.member.MemberService;
import com.smilemall.basic.member.MemberVO;

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
	public String orderinfo(CartVo vo, Model model, HttpSession session) throws Exception {
		
		// 아이디 확보
		String mbsp_id = ((MemberVO) session.getAttribute("login_status")).getMbsp_id();
		vo.setMbsp_id(mbsp_id);
		
		//장바구니 상품목록
		List<CartProductVo> cart_list = cartService.cart_list(mbsp_id);
		
		//이미지 폴더 구분자 변환
		cart_list.forEach(d_vo -> d_vo.setPro_up_folder(d_vo.getPro_up_folder().replace("\\", "/")));

		//상품가격 합계
		int total_price = 0;
		for(int i=0; i < cart_list.size(); i++) {
			total_price += (cart_list.get(i).getPro_price() * cart_list.get(i).getCart_amount());
		}
		
		model.addAttribute("total_price", total_price);
		model.addAttribute("cart_list", cart_list);
		
		
		return "/order/orderinfo";
	}
	
	
	// [주문자 회원정보와 동일]
	@GetMapping("/ordersame")
	public ResponseEntity<MemberVO> ordersame(HttpSession session) throws Exception {
		
		ResponseEntity<MemberVO> entity = null;
		// 아이디 확보
		String mbsp_id = ((MemberVO) session.getAttribute("login_status")).getMbsp_id();
		
		entity = new ResponseEntity<MemberVO> (memberService.login(mbsp_id), HttpStatus.OK);
		
		return entity;
	}
	
	
	
	
	
	
	
	
	
	
	
}
