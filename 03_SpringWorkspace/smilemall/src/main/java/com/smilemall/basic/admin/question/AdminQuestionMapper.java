package com.smilemall.basic.admin.question;

import java.util.List;

import com.smilemall.basic.common.dto.Criteria;
import com.smilemall.basic.member.MemberVo;
import com.smilemall.basic.question.QuestionVo;

public interface AdminQuestionMapper {

	// [문의목록]
	List<QuestionVo> question_list(Criteria cri);
	
	// [문의 전체 데이터 개수]
	int getTotalCount(Criteria cri);
	
	// [문의데이터]
	QuestionVo question_info(Long qa_code);
	
	// [문의수정]
	void question_modify(QuestionVo vo);
	
	// [문의삭제]
	void question_delete(Long qa_code);
	
	// [문의일괄삭제]
	void question_checked_delete(List<Long> qa_code);
	
	// [관리자 답변등록]
	void admin_question_reply(QuestionVo vo);
	
	// [관리자 답변수정]
	void admin_question_reply_modify(QuestionVo vo);
	
	// [관리자 답변삭제]
	void admin_question_reply_delete(QuestionVo vo);
	
	
	// [관리자 메인_문의리스트]
	List<QuestionVo> admin_question_list();

}
