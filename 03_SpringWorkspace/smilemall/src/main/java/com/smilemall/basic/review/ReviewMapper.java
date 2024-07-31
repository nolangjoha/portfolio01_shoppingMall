package com.smilemall.basic.review;

import java.util.List;

public interface ReviewMapper {

	//[상품후기목록]
	List<ReviewVo> rev_list(Integer pro_num);
	
}
