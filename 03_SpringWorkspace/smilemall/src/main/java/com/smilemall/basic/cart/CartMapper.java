package com.smilemall.basic.cart;

import java.util.List;

public interface CartMapper {

	// [장바구니 추가]
	void cart_add(CartVo vo);
	
	// [장바구니 목록]
	List<CartProductVo> cart_list(String mbsp_id);
	
}
