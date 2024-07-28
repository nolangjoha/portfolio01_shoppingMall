package com.smilemall.basic.cart;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {

	private final CartMapper cartMapper;
	
	// [장바구니 추가]
	public void cart_add(CartVo vo) {
		cartMapper.cart_add(vo);
	}
	
	// [장바구니 목록]
	public List<CartProductVo> cart_list(String mbsp_id) {
		return cartMapper.cart_list(mbsp_id);
	}
	
	// [장바구니 목록 삭제] 
	public void cart_del(Long cart_code) {
		cartMapper.cart_del(cart_code);
	}
	
	// [장바구니 수량변경]
	public void cart_change(Long cart_code, int cart_amount) {
		cartMapper.cart_change(cart_code, cart_amount);
	}
	
	// [장바구니 비우기]
	public void cart_empty(String mbsp_id) {
		cartMapper.cart_empty(mbsp_id);
	}
}
