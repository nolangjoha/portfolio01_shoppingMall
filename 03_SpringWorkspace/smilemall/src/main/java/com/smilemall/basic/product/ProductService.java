package com.smilemall.basic.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smilemall.basic.admin.Product.ProductVo;
import com.smilemall.basic.common.dto.Criteria;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductMapper productMapper;

	// [상품리스트]
	public List<ProductVo> pro_list(int cat_code, Criteria cri) {
		return productMapper.pro_list(cat_code, cri);
	}
	
	//[카테고리마다 보이는 상품의 총갯수]
	public int getCountProductByCategory(int cat_code) {
		return productMapper.getCountProductByCategory(cat_code);
	}
	
	//  [장바구니 담기 버튼 클릭시 상품정보 출력]
	public ProductVo pro_info(int pro_num) {
		return productMapper.pro_info(pro_num);
	}
	
	
}
