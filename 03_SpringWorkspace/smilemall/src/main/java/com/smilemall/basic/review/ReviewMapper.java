package com.smilemall.basic.review;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smilemall.basic.common.dto.Criteria;

public interface ReviewMapper {

	//[상품후기목록]
	List<ReviewVo> rev_list(@Param("pro_num") Integer pro_num, @Param("cri") Criteria cri);
	
	// [전체 리뷰개수]
	int getCountReviewByPro_num(Integer pro_num);
	
}
