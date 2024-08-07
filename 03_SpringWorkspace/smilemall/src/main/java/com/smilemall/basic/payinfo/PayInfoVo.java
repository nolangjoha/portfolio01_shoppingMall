package com.smilemall.basic.payinfo;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class PayInfoVo {
	/*
	payinfo, seq_payinfo_id, pk_payinfo_id
	p_id, ord_code, mbsp_id, paymethod, payinfo, p_price, p_status, p_date
	*/
	
	private Integer p_id;			// 결제번호
	private Long ord_code;		// 주문번호
	private String mbsp_id;		// 주문자 아이디
	private String paymethod;	// 결제방법
	private String payinfo;		// 결제정보
	private int p_price;		// 결제가격
	private String p_status;	// 결제상태
	private Date p_date;		// 결제일
	
	
	
	
}
