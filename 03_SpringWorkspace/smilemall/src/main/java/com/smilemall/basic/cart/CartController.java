package com.smilemall.basic.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smilemall.basic.common.util.FileManagerUtils;
import com.smilemall.basic.member.MemberVo;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/cart/*")
public class CartController {

	private final CartService cartService;
	
	// 상품이미지 업로드 경로
	@Value("${file.product.image.dir}")
	private String uploadPath;


	// [장바구니 추가]
	@GetMapping("/cart_add")
	public ResponseEntity<String> cart_add(CartVo vo, HttpSession session) throws Exception {
		
		log.info("장바구니 데이터:" + vo);
		
		//로그인 아이디
		String mbsp_id = ((MemberVo) session.getAttribute("login_status")).getMbsp_id();
		vo.setMbsp_id(mbsp_id);
		
		ResponseEntity<String> entity = null;
		
		//장바구니담은 상품 db저장
		cartService.cart_add(vo);
		
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
	}
	
	
	// [장바구니 목록 페이지]
	@GetMapping("/cart_list")
	public void cart_list(HttpSession session, Model model) throws Exception {
		
		//로그인 아이디
		String mbsp_id = ((MemberVo) session.getAttribute("login_status")).getMbsp_id();
		
		//db작업
		List<CartProductVo> cart_list = cartService.cart_list(mbsp_id);
		
		//이미지 폴더 구분자 변환
		cart_list.forEach(vo -> vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/")));

		//장바구니 상품가격 합계
		int total_price = 0;
		for(int i=0; i < cart_list.size(); i++) {
			total_price += (cart_list.get(i).getPro_price() * cart_list.get(i).getCart_amount());
		}
		
		model.addAttribute("total_price", total_price);
		model.addAttribute("cart_list", cart_list);
	}
	
	// 상품 리스트에서 사용할 이미지
	@GetMapping("/image_display")
	public ResponseEntity<byte[]> image_display(String dateFolderName, String fileName) throws Exception {
		return FileManagerUtils.getFile(uploadPath + dateFolderName, fileName);
	}
	
	// [장바구니 목록 삭제] 
	@GetMapping("/cart_del")
	public String cart_del(Long cart_code) throws Exception {
		cartService.cart_del(cart_code);
		return "redirect:/cart/cart_list";
	}
	
	// [장바구니 수량변경]
	@GetMapping("/cart_change")
	public String cart_change(Long cart_code, int cart_amount) throws Exception {
		cartService.cart_change(cart_code, cart_amount);
		return "redirect:/cart/cart_list";
	}
	
	//  [장바구니 비우기]
	@GetMapping("/cart_empty")
	public String cart_empty(HttpSession session) throws Exception {
		
		//로그인 아이디
		String mbsp_id = ((MemberVo) session.getAttribute("login_status")).getMbsp_id();
		
		cartService.cart_empty(mbsp_id);
		return "redirect:/cart/cart_list";
	}
	
	// [장바구니 선택삭제]
	@PostMapping("/cart_checked_delete")
	public ResponseEntity<String> cart_checked_delete(@RequestParam("cart_code") List<Long> cart_code) throws Exception {
		
		cartService.cart_checked_delete(cart_code);
		
		ResponseEntity<String> entity = null;
		entity = new ResponseEntity<>("success", HttpStatus.OK);
		
		return entity;
	}
	
	
	
}
