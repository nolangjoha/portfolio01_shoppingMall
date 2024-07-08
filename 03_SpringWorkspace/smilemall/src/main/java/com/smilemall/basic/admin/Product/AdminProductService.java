package com.smilemall.basic.admin.Product;

import org.springframework.stereotype.Service;

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
	
	
}
