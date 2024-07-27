package com.smilemall.basic.cart;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartVo {

	// 장바구니 담기시 저장되는 데이터 클래스
/*
cart_tbl
cart_code, pro_num, mbsp_id, cart_amount, cart_date
seq_cart_code
pk_cart_code
*/
	
	private Long cart_code;
	private int pro_num;
	private String mbsp_id;
	private int cart_amount;
	private Date cart_date;
}
