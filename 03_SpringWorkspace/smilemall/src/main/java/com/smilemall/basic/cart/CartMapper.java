package com.smilemall.basic.cart;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CartMapper {

	// [장바구니 추가]
	void cart_add(CartVo vo);
	
	// [장바구니 목록]
	List<CartProductVo> cart_list(String mbsp_id);

	// [장바구니 목록 삭제] 
	void cart_del(Long cart_code);
	
	// [장바구니 수량변경]
	void cart_change(@Param("cart_code") Long cart_code,@Param("cart_amount") int cart_amount);
	
	// [장바구니 비우기]
	void cart_empty(String mbsp_id);
	
	// [장바구니 선택삭제]
	void cart_checked_delete(List<Long> cart_code);
	
}
