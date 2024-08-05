package com.smilemall.basic.order;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class OrderVo {
	/*
	order_tbl, seq_ord_code, pk_ord_code
	ord_code, mbsp_id, ord_name, ord_tel, ord_regdate, 
	ord_addr_zipcode, ord_addr_basic, ord_addr_detail, 
	ord_price, ord_desc, ord_admin_meno
	*/
	
	private Long ord_code;
	private String mbsp_id;
	private String ord_name;
	private String ord_tel;
	private Date ord_regdate;
	private String ord_addr_zipcode;
	private String ord_addr_basic;
	private String ord_addr_detail;
	private int ord_price;
	private String ord_desc;
	private String ord_admin_meno;

}
