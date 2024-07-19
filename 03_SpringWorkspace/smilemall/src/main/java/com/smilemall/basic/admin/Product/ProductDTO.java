package com.smilemall.basic.admin.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 생성
@Getter
@Setter
@ToString
public class ProductDTO {

	
	// 전체상품관리목록에서 체크박스로 받아낼 필드들
	private Integer pro_num;  // 상품코드
	private int pro_price;	  // 상품가격
	private String pro_buy;   // 판매여부
	
}
