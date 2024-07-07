package com.smilemall.basic.admin.Product;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ProductVo {
	/*
	pro_num, cat_code, pro_name, pro_price, pro_discount, pro_publisher, pro_content, pro_up_folder, pro_img, 
	pro_amount, pro_buy, pro_date, pro_updatedate
	*/
	
	private Integer pro_num;		// 상품번호
	private Integer cat_code;		//카테고리 코드
	private String pro_name;		//상품명
	private int pro_price;			//상품가격
	private int pro_discount;		//할인율
	private String pro_publisher;	//제조사
	private String pro_content;		//상품설명
	private String pro_up_folder;	//이미지업로드폴더
	private String pro_img;			//이미지 파일명
	private int pro_amount;			//상품수량
	private String pro_buy;			//판매여부 Y/N
	private Date pro_date;			//상품등록일
	private Date pro_updatedate;	//상품수정일

	
	
	
}
