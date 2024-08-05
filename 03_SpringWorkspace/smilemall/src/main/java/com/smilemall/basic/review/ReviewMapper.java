package com.smilemall.basic.review;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smilemall.basic.common.dto.Criteria;

public interface ReviewMapper {

	//[상품후기목록]
	List<ReviewVo> rev_list(@Param("pro_num") Integer pro_num, @Param("cri") Criteria cri);
	
	// [전체 리뷰개수]
	int getCountReviewByPro_num(Integer pro_num);
	
	// [상품후기 저장] 
	void review_save(ReviewVo vo);
	
	// [상품후기 삭제]
	void review_delete (Long re_code);
	
	// [리뷰수정 페이지]
	ReviewVo review_modify(Long re_code);
	
	// [리뷰수정]
	void review_update(ReviewVo vo);
	
	
}
