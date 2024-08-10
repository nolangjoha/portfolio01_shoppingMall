package com.smilemall.basic.admin.order;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smilemall.basic.common.dto.Criteria;
import com.smilemall.basic.order.OrderVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminOrderService {

	private final AdminOrderMapper adminOrderMapper;
	
	
	// [주문리스트]
	public List<OrderVo> ord_list(Criteria cri) {
		return adminOrderMapper.ord_list(cri);
	}
	
	// [전체 데이터 갯수]
	public int getTotalCount(Criteria cri) {
		return adminOrderMapper.getTotalCount(cri);
	}
	
	
}
