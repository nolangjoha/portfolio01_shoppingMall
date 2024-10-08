package com.smilemall.basic.admin.category;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminCategoryService {

	private final AdminCategoryMapper adminCategoryMapper;
	
	
	// [1차 카테고리 목록]
	public List<CategoryVo> getFirstCategoryList() {
		return adminCategoryMapper.getFirstCategoryList();
	}
	
	// [2차 카테고리 목록]
	public List<CategoryVo> getSecondCategoryList(int cat_prtcode) {
		return adminCategoryMapper.getSecondCategoryList(cat_prtcode);
	}
	
	// [2차 카테고리 정보를 이용한 1차 카테고리 정보]
	public CategoryVo getFirstCategoryBySecondCategory(int cat_code) {
		return adminCategoryMapper.getFirstCategoryBySecondCategory(cat_code);
	}
	
}
