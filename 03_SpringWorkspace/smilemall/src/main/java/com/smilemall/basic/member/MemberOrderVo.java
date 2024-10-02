package com.smilemall.basic.member;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberOrderVo {

	/*
	 ord_code, 
			mbsp_id, 
			ord_name, 
			ord_tel, 
			ord_regdate, 
			ord_addr_zipcode, 
			ord_addr_basic, 
			ord_addr_detail, 
			ord_price, 
			ord_desc,
			
			pro_num,
			dt_amount,
			dt_price,
			
			pro_up_folder,
			pro_img,
			pro_name
	 
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
	
	private Integer pro_num;		// 상품명
	private int dt_amount;			// 주문수량
	private int dt_price;			// 상품가격
	
	private String pro_up_folder;	// 상품이미지 폴더
	private String pro_img;			// 상품 이미지
	private String pro_name;		// 상품명
	
	private Integer p_id;		// 결제번호
	private String paymethod;	// 결제방법
	private String payinfo;		// 결제정보
	private int p_price;		// 결제가격
	private String p_status;	// 결제상태
	private Date p_date;		// 결제일
	
	
	
	
}
