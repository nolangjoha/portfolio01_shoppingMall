package com.smilemall.basic.order;

import org.apache.ibatis.annotations.Param;

import com.smilemall.basic.cart.CartProductVo;

public interface OrderMapper {

	//[주문정보입력]
	void order_insert(OrderVo vo);
	
	//[주문상세테이블]
	void orderDetail_insert(@Param("ord_code") Long ord_code, @Param("mbsp_id") String mbsp_id);
	
	//[바로구매 상품정보 가져오기]
	CartProductVo directOrderProduct(int pro_num);
	
}
