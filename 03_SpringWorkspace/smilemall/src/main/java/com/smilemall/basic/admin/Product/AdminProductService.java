package com.smilemall.basic.admin.Product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smilemall.basic.common.dto.Criteria;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminProductService {

	private final AdminProductMapper adminProductMapper;
	
	//[상품등록]
	public void pro_insert(ProductVo vo) {
		adminProductMapper.pro_insert(vo);
	}
	
	//[상품 리스트]
	public List<ProductVo> pro_list(Criteria cri) {
		return adminProductMapper.pro_list(cri);
	}
	
	// [전체 데이터 갯수]
	public int getTotalCount(Criteria cri) {
		return adminProductMapper.getTotalCount(cri);
	}
	
	// [상품 수정 페이지] : 수정할 데이터 불러오기
	public ProductVo pro_edit(Integer pro_num) {
		return adminProductMapper.pro_edit(pro_num);
	}
	
	// [상품 수정 기능] : 수정한 데이터 DB로 보냄.
	public void pro_edit_ok(ProductVo vo) {
		adminProductMapper.pro_edit_ok(vo);
	};
}
