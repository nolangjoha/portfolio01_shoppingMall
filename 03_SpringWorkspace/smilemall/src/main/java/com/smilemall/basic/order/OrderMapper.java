package com.smilemall.basic.order;

import org.apache.ibatis.annotations.Param;

public interface OrderMapper {

	//[주문정보입력]
	void order_insert(OrderVo vo);
	
	//[주문상세테이블]
	void orderDetail_insert(@Param("ord_code") Long ord_code, @Param("mbsp_id") String mbsp_id);
	
	
}
