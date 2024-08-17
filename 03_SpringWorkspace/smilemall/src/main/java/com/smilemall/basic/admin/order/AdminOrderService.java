package com.smilemall.basic.admin.order;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smilemall.basic.common.dto.Criteria;
import com.smilemall.basic.order.OrderDetailVo;
import com.smilemall.basic.order.OrderVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminOrderService {

	private final AdminOrderMapper adminOrderMapper;
	
	
	// [주문리스트]
	public List<OrderVo> ord_list(Criteria cri, String start_date, String end_date) {
		return adminOrderMapper.ord_list(cri, start_date, end_date);
	}
	
	// [전체 데이터 갯수]
	public int getTotalCount(Criteria cri, String start_date, String end_date) {
		return adminOrderMapper.getTotalCount(cri, start_date, end_date);
	}
	
	
	// [주문자정보(수령인:order_tbl) 가져오기]
	public OrderVo order_info(Long ord_code) {
		return adminOrderMapper.order_info(ord_code);
	}
	
	// [주문자상세정보(상품:ordetail_tbl) 가져오기]
	public List<OrderDetailInfoVo> order_detail_info(Long ord_code) {
		return adminOrderMapper.order_detail_info(ord_code);
	}
	
	// [상품 수량의 합계를 계산]
	public int calculateTotalAmount(Long ord_code) {
        List<OrderDetailInfoVo> orderDetailPrice = adminOrderMapper.order_detail_info(ord_code);
        int totalAmount = 0;
        
        for(OrderDetailInfoVo orderDetail : orderDetailPrice) {
        	totalAmount += orderDetail.getDt_price();
        }
        return totalAmount;
    }
	
	// [주문자(수령인)정보 수정]
	public void order_receiver_modify(OrderVo vo) {
		adminOrderMapper.order_receiver_modify(vo);
	}
	
	// [[주문목록 삭제]]
	@Transactional
	public void order_delete_all(Long ord_code) {
		adminOrderMapper.order_delete(ord_code);
		adminOrderMapper.ordetail_delete(ord_code);
		adminOrderMapper.payinfo_delete(ord_code);
	}
	
	
}
