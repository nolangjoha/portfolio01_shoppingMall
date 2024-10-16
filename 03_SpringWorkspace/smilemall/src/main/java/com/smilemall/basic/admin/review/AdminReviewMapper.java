package com.smilemall.basic.admin.review;

import java.util.List;

import com.smilemall.basic.common.dto.Criteria;
import com.smilemall.basic.review.ReviewVo;

public interface AdminReviewMapper {

	// [리뷰목록]
	List<ReviewVo> rev_list(Criteria cri);
	
	// [전체 데이터 개수]
	int getTotalCount(Criteria cri);
	
	//[상품후기 데이터]
	ReviewVo rev_info(Long re_code);
	
	//[상품후기수정]
	void rev_modify(ReviewVo vo);
	
	//[상품후기삭제]
	void rev_delete(Long re_code);
	
	//[상품후기 일괄삭제]
	void rev_checked_delete(List<Long> re_code);
	
	//[관리자 답변등록]
	void admin_rev_reply(ReviewVo vo);
	
	//[관리자 답변수정]
	void admin_rev_modify(ReviewVo vo);
	
	//[관리자 답변삭제]
	void admin_rev_delete(ReviewVo vo);
	
	// [관리자 메인_리뷰리스트]
	List<ReviewVo> admin_rev_list();
}
