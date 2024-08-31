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
	ord_price, ord_desc, ord_admin_memo
	*/
	
	private Long ord_code;				// 주문코드
	private String mbsp_id;				// 주문아이디
	private String ord_name;			// 주문자(수령자) 이름
	private String ord_tel;				// 주문자(수령자) 연락처
	private Date ord_regdate;			// 주문날짜
	private String ord_addr_zipcode;	// 우편번호
	private String ord_addr_basic;		// 기본주소
	private String ord_addr_detail;		// 상세주소	
	private int ord_price;				// 결제 금액
	private String ord_desc;			// 배송요청
	private String ord_admin_memo;		// 관리자 메모

}
