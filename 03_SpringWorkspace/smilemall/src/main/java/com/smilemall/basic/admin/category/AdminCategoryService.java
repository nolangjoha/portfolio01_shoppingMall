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
	
}
