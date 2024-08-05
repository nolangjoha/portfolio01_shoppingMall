package com.smilemall.basic.review;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smilemall.basic.common.dto.Criteria;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {
 
	private final ReviewMapper reviewMapper;
	
	//[상품후기목록]
	public List<ReviewVo> rev_list(Integer pro_num, Criteria cri) {
		return reviewMapper.rev_list(pro_num, cri);
	}
	
	// [전체 리뷰개수]
	public int getCountReviewByPro_num(Integer pro_num) {
		return reviewMapper.getCountReviewByPro_num(pro_num);
	}
	
	// [상품후기 저장] 
	public void review_save(ReviewVo vo) {
		reviewMapper.review_save(vo);
	}

	// [상품후기 삭제]
	void review_delete (Long re_code) {
		reviewMapper.review_delete(re_code);
	}

	// [리뷰수정 페이지]
	public ReviewVo review_modify(Long re_code) {
		return reviewMapper.review_modify(re_code);
	}

	// [리뷰수정]
	public void review_update(ReviewVo vo) {
		reviewMapper.review_update(vo);
	}
	
	
}
