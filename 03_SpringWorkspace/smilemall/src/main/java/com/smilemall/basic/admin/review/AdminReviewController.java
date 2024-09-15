package com.smilemall.basic.admin.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smilemall.basic.common.constants.Constants;
import com.smilemall.basic.common.dto.Criteria;
import com.smilemall.basic.common.dto.PageDTO;
import com.smilemall.basic.common.util.FileManagerUtils;
import com.smilemall.basic.review.ReviewVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/community/review/*")
public class AdminReviewController {
	
	private final AdminReviewService adminReviewService;

	
	// [상품후기 목록]
	@GetMapping("/rev_list")
	public void rev_list(Criteria cri, Model model) throws Exception {
		
		//상품후기 출력 개수
		cri.setAmount(Constants.AMIN_REVIEW_LIST_AMOUNT);
		log.info("cri정보 :" + cri);
		
		//상품후기 목록
		List<ReviewVo> rev_list = adminReviewService.rev_list(cri);

		//이미지 폴더 구분자 변환
		rev_list.forEach(vo -> {
			vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
		});
		
		//상품후기 데이터 총합
		int totalCount = adminReviewService.getTotalCount(cri);
		
		model.addAttribute("rev_list", rev_list);
		model.addAttribute("pageMaker", new PageDTO(cri, totalCount));
	}
	
	//[상품후기 수정폼]
	@GetMapping("/rev_modify")
	public void rev_modify_info(@ModelAttribute("cri") Criteria cri, Long re_code, Model model) throws Exception {
		log.info("수정할 후기번호: " + re_code);
		log.info("수정할페이징" + cri);
		//수정데이터
		ReviewVo rev_info = adminReviewService.rev_info(re_code);
		
		//이미지 구분자 변환
		rev_info.setPro_up_folder(rev_info.getPro_up_folder().replace("\\", "/"));
		
		model.addAttribute("review", rev_info);
	}
	
	//[상품후기수정]
	@PostMapping("/rev_modify")
	public String rev_modify(Criteria cri, ReviewVo vo) throws Exception {
		log.info("수정할 데이터" + vo);
		log.info("수정전 페이징 정보" + cri);
		// 데이터 수정
		adminReviewService.rev_modify(vo);
		
		log.info("페이징" + cri);
				
		return "redirect:/admin/community/review/rev_list" + cri.getListLink();
	}
	
	//[상품후기삭제]
	@PostMapping("/rev_delete")
	public String rev_delete(Criteria cri, Long re_code) throws Exception {
		log.info("삭제할 번호:" + re_code);
		
		adminReviewService.rev_delete(re_code);
		
		return "redirect:/admin/community/review/rev_list" + cri.getListLink();
	}
	
	
	//[상품후기 일괄삭제]
	@PostMapping("/rev_checked_delete")
	public ResponseEntity<String> rev_checked_delete(@RequestParam("re_code") List<Long> re_code) throws Exception {
		
		adminReviewService.rev_checked_delete(re_code);
		
		ResponseEntity<String> entity = null;
		entity = new ResponseEntity<>("success", HttpStatus.OK);
		return entity;
	}
		
	//[관리자 답변등록폼]
	@GetMapping("/admin_rev_form")
	public void admin_rev_form(@ModelAttribute("cri") Criteria cri, Long re_code, Model model) throws Exception {
		log.info("답변할 후기번호: " + re_code);
		
		//답변후기 정보
		ReviewVo rev_info = adminReviewService.rev_info(re_code);
		log.info("답변후기 정보: " + rev_info);
		
		//이미지 폴더 구분자 변환
		rev_info.setPro_up_folder(rev_info.getPro_up_folder().replace("\\", "/"));
		
		model.addAttribute("review", rev_info);
	}

	// [관리자 답변등록]
	@PostMapping("/admin_rev_reply")
	public String admin_rev_reply(Criteria cri, ReviewVo vo) throws Exception {
		log.info("답변할 리뷰 데이터" + vo);
		
		// 답변등록
		adminReviewService.rev_admin_reply(vo);
		log.info("페이징"+ cri);
		log.info("등록데이터"+ vo);
		
		return "redirect:/admin/community/review/rev_list" + cri.getListLink();
	}
	
	//[관리자 답변수정폼]
	@GetMapping("/admin_rev_modify")
	public void admin_rev_modify_info(@ModelAttribute("cri") Criteria cri, Long re_code, Model model) throws Exception {
		log.info("수정할 관리자 후기번호: " + re_code);
		
		//관리자 답변후기 정보
		ReviewVo rev_info = adminReviewService.rev_info(re_code);
		//이미지 폴더 구분자 변환
		rev_info.setPro_up_folder(rev_info.getPro_up_folder().replace("\\", "/"));
	
		model.addAttribute("review", rev_info);
	}
	
	//[관리자 답변수정]
	@PostMapping("/admin_rev_modify")
	public String admin_rev_modify(Criteria cri, ReviewVo vo) throws Exception {
		log.info("답변할 리뷰 데이터" + vo);
		
		// 답변수정
		adminReviewService.admin_rev_modify(vo);
		log.info("페이징"+ cri);
		log.info("수정데이터"+ vo);
		
		return "redirect:/admin/community/review/rev_list" + cri.getListLink();
	}
	
	//[관리자 답변삭제]
	@PostMapping("/admin_rev_delete")
	public String admin_rev_delete(Criteria cri, ReviewVo vo) throws Exception {
		log.info("관리자 리뷰 삭제 코드:" + vo.getRe_code());
		
		adminReviewService.admin_rev_delete(vo);
		
		return "redirect:/admin/community/review/rev_list" + cri.getListLink();
	}
	
	
}
