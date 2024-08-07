package com.smilemall.basic.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smilemall.basic.cart.CartMapper;
import com.smilemall.basic.cart.CartProductVo;
import com.smilemall.basic.payinfo.PayInfoMapper;
import com.smilemall.basic.payinfo.PayInfoVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

	private final OrderMapper orderMapper;
	private final PayInfoMapper payInfoMapper;
	private final CartMapper cartMapper; 
	
	
	@Transactional
	public void order_process(OrderVo vo, String mbsp_id, String paymethod, String p_status, String payinfo) {
		
		// 1. 주문테이블 insert
		vo.setMbsp_id(mbsp_id);
		orderMapper.order_insert(vo);
		
		// 2. 주문상세테이블 insert
		orderMapper.orderDetail_insert(vo.getOrd_code(), mbsp_id);
		
		// 3. 결제테이블 insert
		PayInfoVo p_vo = PayInfoVo.builder()
				.ord_code(vo.getOrd_code())
				.mbsp_id(mbsp_id)
				.p_price(vo.getOrd_price())
				.paymethod(paymethod)
				.payinfo(payinfo)
				.p_status(p_status)
				.build();
		
		payInfoMapper.payInfo_insert(p_vo);
		
		
		// 4. 장바구니 테이블 deleten // 결제방식에 따라 if문 추가?
		// 1) 장바구니 전체물품 구매시 
		cartMapper.cart_empty(mbsp_id);
		
		
		
	}
	
	
	//[바로구매 상품정보 가져오기]
	public CartProductVo directOrderProduct(int pro_num) {
		return orderMapper.directOrderProduct(pro_num);
	}

}
