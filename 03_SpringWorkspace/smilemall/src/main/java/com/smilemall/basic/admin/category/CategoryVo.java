package com.smilemall.basic.admin.category;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryVo {

	//-- category_tbl // cat_code, cat_prtcode, cat_name
	
	private Integer cat_code;   // 카테고리 코드 (모든 레벨의 카테고리 저장)
	private int cat_prtcode;	// 상위(부모)카테고리
	private String cat_name;	// 카테고리 이름
	
}
