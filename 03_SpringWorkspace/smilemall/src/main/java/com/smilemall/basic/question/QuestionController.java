package com.smilemall.basic.question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smilemall.basic.common.constants.Constants;
import com.smilemall.basic.common.dto.Criteria;
import com.smilemall.basic.common.dto.PageDTO;
import com.smilemall.basic.member.MemberVo;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/question/*")
public class QuestionController {

	private final QuestionService questionService;
	
	//[상품문의 목록]
	@GetMapping("/question_list/{pro_num}/{page}")
	public ResponseEntity<Map<String,Object>> question_list(@PathVariable("pro_num") int pro_num,@PathVariable("page") int page) throws Exception {
		
		ResponseEntity<Map<String,Object>> entity = null;
		
		Map<String,Object> map = new HashMap<>();
		
		// 페이지
		Criteria cri = new Criteria();
		cri.setAmount(Constants.QUESTION_LIST_AMOUNT); //문의출력개수
		cri.setPageNum(page);
		
		//문의목록 데이터
		List<QuestionVo> question_list = questionService.question_list(pro_num, cri);
		log.info("목록데이터:" +question_list);
		
		// 선택한 상품의 문의데이터 전체
		int revcount = questionService.getCountQuestionByPro_num(pro_num);
		PageDTO pageMaker = new PageDTO(cri, revcount);
		
		map.put("question_list", question_list);
		map.put("pageMaker", pageMaker);

		entity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		
		return entity; 
	}
	
	
	// [상품문의 저장] 
	@PostMapping(value="/question_save", consumes = {"application/json"}, produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> question_save(@RequestBody QuestionVo vo, HttpSession session) throws Exception {
		
		// 아이디 가져오기
		String mbsp_id = ((MemberVo) session.getAttribute("login_status")).getMbsp_id();
		vo.setMbsp_id(mbsp_id);
		
		log.info("상품문의 데이터 : " + vo);
		
		// 문의저장
		questionService.question_save(vo); 
		
		ResponseEntity<String> entity = null;
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
	
		return entity;
	}
	
	
	// [상품문의 삭제] 
	@DeleteMapping("/question_delete/{qa_code}")
	public ResponseEntity<String> question_delete(@PathVariable("qa_code") Long qa_code) throws Exception  {
		
		ResponseEntity<String> entity = null;
		
		log.info("삭제문의 코드: " + qa_code);
		
		questionService.question_delete(qa_code);
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
			
		return entity;
	}
	
	
	// [문의수정 페이지]
	@GetMapping("/question_modify/{qa_code}")
	public ResponseEntity<QuestionVo> question_modify(@PathVariable("qa_code") Long qa_code) throws Exception {
		
		ResponseEntity<QuestionVo> entity = null;
		
		entity = new ResponseEntity<QuestionVo>(questionService.question_modify(qa_code),HttpStatus.OK);
		
		log.info("수정문의코드" + qa_code); 
		
		return entity;
	}
	
	// [문의수정]
	@PutMapping("/question_modify")
	public ResponseEntity<String> question_modify(@RequestBody QuestionVo vo) throws Exception {
		
		ResponseEntity<String> entity = null;
		
		questionService.question_update(vo);
		
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
	}

	
	
	
	
	
	
	
	
	
}
