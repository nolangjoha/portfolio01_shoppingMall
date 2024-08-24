package com.smilemall.basic.admin.Product;

import java.util.ArrayList;
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
	
	// [상품삭제] : DB데이터 삭제
	public void pro_delete(Integer pro_num) {
		adminProductMapper.pro_delete(pro_num);
	}
	
	// [상품 일괄수정]
	public void pro_checked_modify(List<Integer>pro_num_arr, List<Integer>pro_price_arr, List<String> pro_buy_arr) {
		
		//mapper에서 쓰일 리스트객체
		List<ProductDTO> pro_modify_list = new ArrayList<>();
		
		// 삭제할 상품코드 만큼 for문 반복
		for(int i=0; i<pro_num_arr.size(); i++) {
			
			// 체크박스로 수정할 객체들
			ProductDTO productDTO = new ProductDTO(pro_num_arr.get(i), pro_price_arr.get(i), pro_buy_arr.get(i));

			//mapper에서 쓰일 리스트객체에 추가한다.
			pro_modify_list.add(productDTO);
		}
		
		// 체크박스한 목록들을 수정한다.
		adminProductMapper.pro_checked_modify(pro_modify_list);
	}

	// [상품 일괄삭제]
	public void pro_checked_delete(List<Integer>pro_num) {
		adminProductMapper.pro_checked_delete(pro_num);
	}
	
}
