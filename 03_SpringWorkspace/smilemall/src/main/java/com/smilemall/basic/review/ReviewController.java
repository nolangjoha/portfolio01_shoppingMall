package com.smilemall.basic.review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smilemall.basic.common.constants.Constants;
import com.smilemall.basic.common.dto.Criteria;
import com.smilemall.basic.common.dto.PageDTO;
import com.smilemall.basic.member.MemberVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/review/*")
public class ReviewController {

	private final ReviewService reviewService;
	

	// [상품후기 목록]
	@GetMapping("/revlist/{pro_num}/{page}")
	public ResponseEntity<Map<String,Object>> revlist(@PathVariable("pro_num") int pro_num,@PathVariable("page") int page) throws Exception {
		
		ResponseEntity<Map<String,Object>> entity = null;
		
		Map<String,Object> map = new HashMap<>();
		
		// 페이지
		Criteria cri = new Criteria();
		cri.setAmount(Constants.REVIEW_LIST_AMOUNT); //리뷰출력개수
		cri.setPageNum(page);
		
		//후기목록 데이터
		List<ReviewVo> revlist = reviewService.rev_list(pro_num, cri);
		
		// 선택한 상품의 후기 데이터 전체
		int revcount = reviewService.getCountReviewByPro_num(pro_num);
		PageDTO pageMaker = new PageDTO(cri, revcount);
		
		map.put("revlist", revlist);
		map.put("pageMaker", pageMaker);

		entity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		
		return entity; 
		
	}
	
	
	// [상품후기 저장] 
	@PostMapping(value="/review_save", consumes = {"application/json"}, produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> review_save(@RequestBody ReviewVo vo, HttpSession session) throws Exception {
		
		// 아이디 가져오기
		String mbsp_id = ((MemberVO) session.getAttribute("login_status")).getMbsp_id();
		vo.setMbsp_id(mbsp_id);
		
		log.info("상품후기데이터 : " + vo);
		
		// 후기저장
		reviewService.review_save(vo); 
		
		ResponseEntity<String> entity = null;
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
	
		return entity;
	}
	
	
	// [상품후기 삭제]
	@DeleteMapping("/review_delete/{re_code}")
	public ResponseEntity<String> review_delete(@PathVariable("re_code") Long re_code) throws Exception  {
		
		ResponseEntity<String> entity = null;
		
		log.info("장바구니 코드: " + re_code);
		
		reviewService.review_delete(re_code);
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
			
		return entity;
	}
	
	
	
	
	
}
