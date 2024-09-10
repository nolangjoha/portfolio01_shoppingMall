package com.smilemall.basic.admin.review;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smilemall.basic.common.dto.Criteria;
import com.smilemall.basic.review.ReviewVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminReviewService {

	private final AdminReviewMapper adminReviewMapper;
	

	// [리뷰목록]
	public List<ReviewVo> rev_list(Criteria cri) {
		return adminReviewMapper.rev_list(cri);
	}
	
	// [전체 데이터 개수]
	int getTotalCount(Criteria cri) {
		return adminReviewMapper.getTotalCount(cri);
	}

	//[상품후기 수정데이터]
	public ReviewVo rev_info(Long re_code) {
		return adminReviewMapper.rev_info(re_code);
	}

	//[상품후기수정]
	public void rev_modify(ReviewVo vo) {
		adminReviewMapper.rev_modify(vo);
	}

	//[상품후기삭제]
	public void rev_delete(Long re_code) {
		adminReviewMapper.rev_delete(re_code);
	}
	
	//[상품후기 일괄삭제]
	public void rev_checked_delete(List<Long> re_code) {
		adminReviewMapper.rev_checked_delete(re_code);
	}
		
	//[관리자 답변등록]
	public void rev_admin_reply(ReviewVo vo) {
		adminReviewMapper.admin_rev_reply(vo);
	}
	
	//[관리자 답변수정]
	public void admin_rev_modify(ReviewVo vo) {
		adminReviewMapper.admin_rev_modify(vo);
	}

	//[관리자 답변삭제]
	public void admin_rev_delete(ReviewVo vo) {
		adminReviewMapper.admin_rev_delete(vo);
	}
	
}
