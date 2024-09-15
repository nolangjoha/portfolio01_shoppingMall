package com.smilemall.basic.admin.question;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smilemall.basic.common.dto.Criteria;
import com.smilemall.basic.question.QuestionVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class AdminQuestionService {

	private final AdminQuestionMapper adminQuestionMapper;
	
	// [문의목록]
	public List<QuestionVo> question_list(Criteria cri) {
		return adminQuestionMapper.question_list(cri);
	}
		
	// [문의 전체 데이터 개수]
	public int getTotalCount(Criteria cri) {
		return adminQuestionMapper.getTotalCount(cri);
	}
	
	// [문의데이터]
	public QuestionVo question_info(Long qa_code) {
		return adminQuestionMapper.question_info(qa_code);
	}
	
	//[문의데이터 수정]
	public void question_modify(QuestionVo vo) {
		adminQuestionMapper.question_modify(vo);
	}
	
	// [문의데이터 삭제]
	public void question_delete(Long qa_code) {
		adminQuestionMapper.question_delete(qa_code);
	}
	
	// [문의일괄삭제]
	public void question_checked_delete(List<Long> qa_code) {
		adminQuestionMapper.question_checked_delete(qa_code);
	}
	
	// [관리자 답변등록]
	public void admin_question_reply(QuestionVo vo) {
		adminQuestionMapper.admin_question_reply(vo);
	}
	
	// [관리자 답변수정]
	public void admin_question_reply_modify(QuestionVo vo) {
		adminQuestionMapper.admin_question_reply_modify(vo);
	}
	
	// [관리자 답변삭제]
	public void admin_question_reply_delete(QuestionVo vo) {
		adminQuestionMapper.admin_question_reply_delete(vo);
	}
	
	
	
}
