package com.smilemall.basic.admin.question;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smilemall.basic.common.constants.Constants;
import com.smilemall.basic.common.dto.Criteria;
import com.smilemall.basic.common.dto.PageDTO;
import com.smilemall.basic.question.QuestionVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/community/question/*")
public class AdminQuestionController {

	private final AdminQuestionService adminQuestionService;
	
	// [문의목록]
	@GetMapping("/question_list")
	public void question_list(Criteria cri, Model model) throws Exception {
		
		//상품문의 출력 개수
		cri.setAmount(Constants.AMIN_QUESTION_LIST_AMOUNT);
		log.info("cri정보 :" + cri);
		
		//상품문의 목록
		List<QuestionVo> question_list = adminQuestionService.question_list(cri);

		//이미지 폴더 구분자 변환
		question_list.forEach(vo -> {
			vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
		});
		
		//상품문의 데이터 총합
		int totalCount = adminQuestionService.getTotalCount(cri);
		
		model.addAttribute("question_list", question_list);
		model.addAttribute("pageMaker", new PageDTO(cri, totalCount));
	}
	
	
	// [상품문의 수정폼]
	@GetMapping("/question_modify")
	public void question_modify_info(@ModelAttribute("cri") Criteria cri, Long qa_code, Model model) throws Exception {
		log.info("수정할 문의번호: " + qa_code);
		log.info("수정페이지: " + cri);
		
		// 수정할 문의 데이터
		QuestionVo question_info = adminQuestionService.question_info(qa_code);
		//이미지 구분자 변환
		question_info.setPro_up_folder(question_info.getPro_up_folder().replace("\\", "/"));
		
		model.addAttribute("question", question_info);
	}
	
	
	// [상품문의수정]
	@PostMapping("/question_modify")
	public String question_modify(Criteria cri, QuestionVo vo, RedirectAttributes rttr) throws Exception {
		log.info("수정할 데이터:" + vo);
		log.info("수정전 페이징:"+cri);
		
		//문의수정
		adminQuestionService.question_modify(vo);
		
		log.info("수정후 페이징:" + cri);
		
		//수정완료 메세지
		rttr.addFlashAttribute("msg", "modify");
		
		return "redirect:/admin/community/question/question_list" + cri.getListLink();
	}
	
	// [상품문의삭제]
	@PostMapping("/question_delete")
	public String question_delete(Criteria cri, Long qa_code, RedirectAttributes rttr) throws Exception {
		log.info("삭제문의코드:" + qa_code);
		
		adminQuestionService.question_delete(qa_code);
		
		//수정완료 메세지
		rttr.addFlashAttribute("msg", "delete");
		
		return "redirect:/admin/community/question/question_list" + cri.getListLink();
	}
	
	
	// [상품문의 일괄삭제]
	@PostMapping("/question_checked_delete")
	public ResponseEntity<String> question_checked_delete(@RequestParam("qa_code") List<Long> qa_code) throws Exception {
		adminQuestionService.question_checked_delete(qa_code);
		
		ResponseEntity<String> entity = null;
		entity = new ResponseEntity<>("success", HttpStatus.OK);
		return entity;
	}
	
	
	// [관리자 답변등록폼]
	@GetMapping("/admin_question_reply_form")
	public void admin_question_reply_form(@ModelAttribute("cri") Criteria cri, Long qa_code, Model model) throws Exception {
		log.info("답변할 문의번호:" + qa_code);
		
		// 답변할 문의 정보
		QuestionVo question_info = adminQuestionService.question_info(qa_code);
		log.info("답변할 문의 정보:" + question_info);
		
		//이미지 구분자 변환
		question_info.setPro_up_folder(question_info.getPro_up_folder().replace("\\", "/"));
		
		model.addAttribute("question", question_info);
		
	}
	
	
	// [관리자 답변등록]
	@PostMapping("/admin_question_reply")
	public String admin_question_reply(Criteria cri, QuestionVo vo, RedirectAttributes rttr) throws Exception {
		log.info("답변할 문의 데이터:" + vo);
		
		//답변등록
		adminQuestionService.admin_question_reply(vo);
		log.info("등록데이터: " + vo);
		
		//수정완료 메세지
		rttr.addFlashAttribute("msg", "admin_reply");
		
		return "redirect:/admin/community/question/question_list" + cri.getListLink();
	}
	
	
	// [관리자 답변수정폼]
	@GetMapping("/admin_question_reply_modify")
	public void admin_question_reply_modify(@ModelAttribute("cri") Criteria cri, Long qa_code, Model model) throws Exception {
		log.info("수정할 관리자 문의번호:" + qa_code);
		
		// 답변할 문의 정보
		QuestionVo question_info = adminQuestionService.question_info(qa_code);
		log.info("답변할 문의 정보:" + question_info);
		
		//이미지 구분자 변환
		question_info.setPro_up_folder(question_info.getPro_up_folder().replace("\\", "/"));
		
		model.addAttribute("question", question_info);
	}
	
	
	// [관리자 답변수정]
	@PostMapping("/admin_question_reply_modify")
	public String admin_question_reply_modify(Criteria cri, QuestionVo vo, RedirectAttributes rttr) throws Exception {
		log.info("답변할 수정문의:" + vo);
		
		//답변수정
		adminQuestionService.admin_question_reply_modify(vo);
		log.info("수정데이터:"+ vo);
		
		//수정완료 메세지
		rttr.addFlashAttribute("msg", "admin_reply_modify");
		
		return "redirect:/admin/community/question/question_list" + cri.getListLink();
	}
	
	
	// [관리자 답변삭제]
	@PostMapping("/admin_question_reply_delete")
	public String admin_question_reply_delete (Criteria cri, QuestionVo vo, RedirectAttributes rttr) throws Exception {
		log.info("관리자 답변 삭제코드:" + vo.getQa_code());
	
		adminQuestionService.admin_question_reply_delete(vo);
		
		//삭제완료 메세지
		rttr.addFlashAttribute("msg", "admin_reply_delete");
		
		return "redirect:/admin/community/question/question_list" + cri.getListLink();
	}
	
	
	
	
	
	
}
