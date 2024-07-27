package com.smilemall.basic.cart;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartProductVo {
	// 장바구니에 담긴 상품목록 클래스
	
	private Long cart_code;
	private int pro_num;
	private String pro_name;
	private String pro_up_folder;
	private String pro_img;
	private int pro_price;
	private int cart_amount;
	
}
