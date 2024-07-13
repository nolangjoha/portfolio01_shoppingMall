package com.smilemall.basic.admin.Product;

import java.util.List;

import com.smilemall.basic.common.dto.Criteria;

public interface AdminProductMapper {

	
	//[상품등록]
	void pro_insert(ProductVo vo);
	
	// [상품리스트]
	List<ProductVo> pro_list(Criteria cri);
	
	// [전체 데이터 갯수]
	int getTotalCount(Criteria cri);
	
	
}
