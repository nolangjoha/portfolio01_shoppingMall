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
	
	
}
