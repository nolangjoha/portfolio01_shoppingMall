package com.smilemall.basic.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDetailVo {
	/*
	ordetail_tbl, pk_ordetail_code
	ord_code, pro_num, dt_amount, dt_price
	*/
	
	private Long ord_code;
	private int pro_num;
	private int dt_amount;
	private int dt_price;
	
}
