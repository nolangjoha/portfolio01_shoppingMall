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
	
	// [상품 수정 페이지] : 수정할 데이터 불러오기
	ProductVo pro_edit(Integer pro_num);
	
	// [상품 수정 기능] : 수정한 데이터 DB로 보냄.
	void pro_edit_ok(ProductVo vo);
	
	// [상품삭제] : DB데이터 삭제
	void pro_delete(Integer pro_num);
	
	// [상품 일괄수정]
	void pro_checked_modify(List<ProductDTO> pro_modify_list);
	

	
	
	
}
