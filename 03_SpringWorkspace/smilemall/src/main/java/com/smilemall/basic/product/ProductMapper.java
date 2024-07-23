package com.smilemall.basic.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smilemall.basic.admin.Product.ProductVo;
import com.smilemall.basic.common.dto.Criteria;

public interface ProductMapper {

	// [상품리스트]
	List<ProductVo> pro_list(int cat_code);
 	
}
