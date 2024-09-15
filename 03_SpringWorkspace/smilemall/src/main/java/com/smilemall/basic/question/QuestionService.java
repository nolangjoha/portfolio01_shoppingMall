package com.smilemall.basic.question;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smilemall.basic.common.dto.Criteria;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionService {

	private final QuestionMapper questionMapper;
	
	//[문의목록]
	public List<QuestionVo> question_list(Integer pro_num, Criteria cri) {
		return questionMapper.question_list(pro_num, cri);
	}
	
	//[전체 문의개수]
	public int getCountQuestionByPro_num(Integer pro_num) {
		return questionMapper.getCountQuestionByPro_num(pro_num);
	}
	
	// [상품문의 저장] 
	public void question_save(QuestionVo vo) {
		questionMapper.question_save(vo);
	}
	
	// [상품문의 삭제]
	public void question_delete (Long qa_code) {
		questionMapper.question_delete(qa_code);
	}
	
	// [문의수정 페이지]
	public QuestionVo question_modify(Long qa_code) {
		return questionMapper.question_modify(qa_code);
	}
			
	// [문의수정]
	public void question_update(QuestionVo vo) {
		questionMapper.question_update(vo);
	}

	
	
	
}
