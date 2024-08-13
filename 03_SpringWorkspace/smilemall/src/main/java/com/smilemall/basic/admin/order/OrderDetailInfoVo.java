package com.smilemall.basic.admin.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDetailInfoVo {

/*
ot.ord_code, ot.pro_num, ot.dt_amount, ot.dt_price, p.pro_name, p.pro_up_folder, p.pro_img 
 */	
	
	
	private Long ord_code;			// 주문코드
	private Integer pro_num;		// 상품명
	private int dt_amount;			// 주문수량
	private int dt_price;			// 상품가격
	private String pro_up_folder;	// 상품이미지 폴더
	private String pro_img;			// 상품 이미지
	private String pro_name;		// 상품명
	
	
	
}
