package com.smilemall.basic.admin.category;

import java.util.List;

public interface AdminCategoryMapper {

	
	// [1차 카테고리 목록]
	List<CategoryVo> getFirstCategoryList();
	
	// [2차 카테고리 목록]
	List<CategoryVo> getSecondCategoryList(int cat_prtcode); //cat_prtcode : 상위 카테고리
	
	
	
}
