package com.smilemall.basic.admin.category;

import java.util.List;

public interface AdminCategoryMapper {

	
	// [1차 카테고리 목록]
	List<CategoryVo> getFirstCategoryList();
	
	// 2차카테고리 목록
	List<CategoryVo> getSecondCategoryList(int cat_prtcode);
	
	// [2차 카테고리 정보를 이용한 1차 카테고리 정보]
	CategoryVo getFirstCategoryBySecondCategory(int cat_code);
	
}
