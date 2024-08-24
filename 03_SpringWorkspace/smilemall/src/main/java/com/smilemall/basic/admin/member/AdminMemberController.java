package com.smilemall.basic.admin.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smilemall.basic.common.constants.Constants;
import com.smilemall.basic.common.dto.Criteria;
import com.smilemall.basic.common.dto.PageDTO;
import com.smilemall.basic.member.MemberVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/member")
public class AdminMemberController {

	private final AdminMemberService adminMemberService;
	
	// [회원목록]
	@GetMapping("/member_list")
	public void member_list(Criteria cri, Model model) throws Exception {
		
		cri.setAmount(Constants.AMIN_MEMBER_LIST_AMOUNT);
		log.info("cri정보 :" + cri);
		
		List<MemberVo> member_list = adminMemberService.member_list(cri);
		
		int totalCount = adminMemberService.getTotalCount(cri);
		
		model.addAttribute("member_list", member_list);
		model.addAttribute("pageMaker", new PageDTO(cri, totalCount));
	}

	
	// [회원상세정보]
	@GetMapping("/member_info")
	public ResponseEntity<Map<String, Object>> member_info(String mbsp_id, Model model) throws Exception {
		log.info("수정회원 아이디: " + mbsp_id);
		
		ResponseEntity<Map<String, Object>> entity = null;
		
		Map<String, Object> map = new HashMap<>();
		
		MemberVo vo = adminMemberService.member_info(mbsp_id);
		map.put("member", vo);
		
		entity = new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
		
		return entity;
	}
	
	
	// [회원상세정보 수정하기]
	@PostMapping("/member_modify")
	public ResponseEntity<String> member_modify(MemberVo vo) throws Exception {
		log.info("수정정보: "+ vo);
	
		ResponseEntity<String> entity = null;
		adminMemberService.member_modify(vo);
		
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
	}
	
	// [회원정보 삭제하기]
	@PostMapping("/member_delete")
	public String member_delete(Criteria cri, @RequestParam("mbsp_id") String mbsp_id) {
		log.info("삭제아이디 : " +  mbsp_id);
		adminMemberService.member_delete(mbsp_id);
		
		return "redirect:/admin/member/member_list"+ cri.getListLink();
	}
	
	
	// [회원정보 일괄삭제]
	@PostMapping("/member_checked_delete")
	public ResponseEntity<String> member_checked_delete(@RequestParam("mbsp_id") List<String>mbsp_id) {
		adminMemberService.member_checked_delete(mbsp_id);
		ResponseEntity<String> entity = null;
		entity = new ResponseEntity<> ("success", HttpStatus.OK);
		
		return entity;
	}
	
	
	
}
