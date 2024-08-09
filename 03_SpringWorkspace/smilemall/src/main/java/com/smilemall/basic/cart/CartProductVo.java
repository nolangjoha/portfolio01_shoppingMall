package com.smilemall.basic.cart;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartProductVo {
	// 장바구니에 담긴 상품목록 클래스
	
	private Long cart_code;			// 장바구니 코드
	private int pro_num;			// 상품번호
	private String pro_name;		// 상품명
	private String pro_up_folder;	// 상품이미지 폴더
	private String pro_img;			// 상품이미지파일
	private int pro_price;			// 상품가격
	private int cart_amount;		// 장바구니에 담은 양
	
}
