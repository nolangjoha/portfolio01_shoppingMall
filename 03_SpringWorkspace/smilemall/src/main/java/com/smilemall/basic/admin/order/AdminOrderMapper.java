package com.smilemall.basic.admin.order;

import java.util.List;

import com.smilemall.basic.common.dto.Criteria;
import com.smilemall.basic.order.OrderVo;

public interface AdminOrderMapper {

	// [주문리스트]
	List<OrderVo> ord_list(Criteria cri);
	
	// [전체 데이터 갯수]
	int getTotalCount(Criteria cri);

	// [주문자정보(수령인:order_tbl) 가져오기]
	OrderVo order_info(Long ord_code);
	
	// [주문자상세정보(상품:ordetail_tbl) 가져오기]
	List<OrderDetailInfoVo> order_detail_info(Long ord_code);

	











}
