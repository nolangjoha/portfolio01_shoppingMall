package com.smilemall.basic.member;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member/*")
public class MemberController {

	private final MemberService memberService;
	
	
	// [회원가입 폼]
	@GetMapping("join")
	public void joinForm() {
		log.info("joinForm 실행");
	}
	
	// [아이디 중복체크]
	@GetMapping("/idCheck")
	public ResponseEntity<String> idCheck(String mbsp_id) throws Exception{
		log.info("아이디 중복체크(idCheck):" + mbsp_id);
		
		ResponseEntity<String> entity = null;
		
		String idUse = "";
		
		if(memberService.idCheck(mbsp_id) != null) {	//사용자가 입력한 아이디가 DB에 존재하면
			idUse ="no";								//no : 사용불가
		}else {
			idUse ="yes";								//yes : 사용가능
		}
			
		entity = new ResponseEntity<String>(idUse, HttpStatus.OK);
		
		return entity;
	}
	
	// [닉네임 중복체크]
	@GetMapping("/nickCheck")
	public ResponseEntity<String> nickCheck(String mbsp_nick) throws Exception{
		log.info("닉네임 중복체크(nickCheck):" + mbsp_nick);
		
		ResponseEntity<String> entity = null;
		
		String nickUse = "";
		
		if(memberService.nickCheck(mbsp_nick) != null) {	//사용자가 입력한 네임이가 DB에 존재하면
			nickUse ="no";								//no : 사용불가
		}else {
			nickUse ="yes";								//yes : 사용가능
		}
			
		entity = new ResponseEntity<String>(nickUse, HttpStatus.OK);
		
		return entity;
	}
	
}
