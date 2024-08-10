package com.smilemall.basic.admin.order;

import java.util.List;

import com.smilemall.basic.common.dto.Criteria;
import com.smilemall.basic.order.OrderVo;

public interface AdminOrderMapper {

	// [주문리스트]
	List<OrderVo> ord_list(Criteria cri);
	
	// [전체 데이터 갯수]
	int getTotalCount(Criteria cri);

















}
