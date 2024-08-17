package com.smilemall.basic.admin.order;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smilemall.basic.common.dto.Criteria;
import com.smilemall.basic.order.OrderVo;

public interface AdminOrderMapper {

	// [주문리스트]
	List<OrderVo> ord_list(@Param("cri") Criteria cri, @Param("start_date") String start_date, @Param("end_date") String end_date);
	
	// [전체 데이터 갯수]
	int getTotalCount(@Param("cri") Criteria cri, @Param("start_date") String start_date, @Param("end_date") String end_date);

	// [주문자정보(수령인:order_tbl) 가져오기]
	OrderVo order_info(Long ord_code);
	
	// [주문자상세정보(상품:ordetail_tbl) 가져오기]
	List<OrderDetailInfoVo> order_detail_info(Long ord_code);

	// [주문자(수령인)정보 수정]
	void order_receiver_modify(OrderVo vo);

	// [주문목록 삭제]
	void order_delete(Long ord_code); 	 // 1) 주문자정보
	void ordetail_delete(Long ord_code); // 2) 주문상세(상품)정보
	void payinfo_delete(Long ord_code);  // 3) 주문결제정보



}
