package com.smilemall.basic.mail;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/email/*")
public class EmailController {

	private final EmailService emailService;
	
	// [인증코드 발송]
	@GetMapping("/authcode")
	public ResponseEntity<String> authcode(String type, EmailDTO dto, HttpSession session){
		
		log.info("dto확인:" + dto);
		ResponseEntity<String> entity = null;
		
		//인증코드 생성
		String authcode="";
		for(int i=0; i<6; i++) {
			authcode += String.valueOf((int)(Math.random() * 10));
		}
		log.info("인증코드: "+ authcode);
		
		// 비교목적, 세션방식으로 인증코드 메모리에 저장
		session.setAttribute("authcode", authcode);
		
		// 메일 발송
		try {
			//메일 제목
			if(type.equals("emailJoin")) {
				dto.setSubject("Smile Mall 회원가입 인증코드 입니다.");
			}else if(type.equals("emailID")) {
				dto.setSubject("Smile Mall 아이디찾기 인증코드 입니다.");
			}else if(type.equals("emailPw")) {
				dto.setSubject("Smile Mall 비밀번호 찾기 인증코드 입니다.");
			}
			
			emailService.sendMail(type, dto, authcode);  
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
			}
		catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
	
	
		// [인증코드 확인]
		@GetMapping("/email/confirm_authcode")
		public ResponseEntity<String> confirm_authcode(String authcode, HttpSession session) {
			
			ResponseEntity<String> entity = null;
			
			// 세션이 유지되고 있을 경우
			if(session.getAttribute("authcode") != null) {
				
				if(authcode.equals(session.getAttribute("authcode"))) {
					entity = new ResponseEntity<String> ("success", HttpStatus.OK);
					session.removeAttribute("authcode"); // 성공시 서버측 메모리 제거
				}else {
					entity = new ResponseEntity<String> ("fail", HttpStatus.OK);
				}
			}else { //세션이 소멸되었을 경우
				entity = new ResponseEntity<String> ("request", HttpStatus.OK);
			}
				
			return entity;
		}
	
	
}
