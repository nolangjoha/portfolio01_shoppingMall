package com.smilemall.basic.question;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smilemall.basic.common.dto.Criteria;

public interface QuestionMapper {

	//[문의목록]
	List<QuestionVo> question_list(@Param("pro_num") Integer pro_num, @Param("cri") Criteria cri);
	
	// [전체 문의개수]
	int getCountQuestionByPro_num(Integer pro_num);
	
	// [상품문의 저장] 
	void question_save(QuestionVo vo);
	
	// [상품문의 삭제]
	void question_delete (Long qa_code);
	
	// [문의수정 페이지]
	QuestionVo question_modify(Long qa_code);
		
	// [문의수정]
	void question_update(QuestionVo vo);
	
	
	
}
