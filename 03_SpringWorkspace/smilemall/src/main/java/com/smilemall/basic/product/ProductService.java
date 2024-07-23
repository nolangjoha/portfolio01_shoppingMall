package com.smilemall.basic.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smilemall.basic.admin.Product.ProductVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductMapper productMapper;

	// [상품리스트]
	public List<ProductVo> pro_list(int cat_code) {
		return productMapper.pro_list(cat_code);
	}
	
	
}
