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
}
