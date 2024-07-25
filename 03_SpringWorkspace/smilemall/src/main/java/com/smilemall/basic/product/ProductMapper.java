package com.smilemall.basic.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smilemall.basic.admin.Product.ProductVo;
import com.smilemall.basic.common.dto.Criteria;

public interface ProductMapper {

	// [상품리스트]
	List<ProductVo> pro_list(@Param("cat_code") int cat_code, @Param("cri") Criteria cri);
 	
	//[카테고리마다 보이는 상품의 총갯수]
	int getCountProductByCategory(int cat_code);
	
	//  [장바구니 담기 버튼 클릭시 상품정보 출력]
	ProductVo pro_info(int pro_num);
	
	
}
