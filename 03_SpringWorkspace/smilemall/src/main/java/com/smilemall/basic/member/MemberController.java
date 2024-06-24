package com.smilemall.basic.member;

import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smilemall.basic.mail.EmailDTO;
import com.smilemall.basic.mail.EmailService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member/*")
public class MemberController {

	
	private final PasswordEncoder passwordEncoder;
	
	private final MemberService memberService;
	
	private final EmailService emailService;
	
	
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
	
	
	// [회원가입 저장]
	@PostMapping("/join")
	public String joinOk(MemberVO vo) throws Exception {
		log.info("회원정보(joinOk) :" + vo); 
		
		//비밀번호 암호화
		vo.setMbsp_password(passwordEncoder.encode(vo.getMbsp_password()));
		
		//사용자가 입력한 회원정보 DB저장.
		memberService.join(vo);
		return "redirect:/";  // 
	}
	
	
	// [로그인 폼]
	@GetMapping("/login")
	public void loginForm() {
		log.info("loginForm 실행");
	}
	
	
	// [로그인 작업]
	@PostMapping("/login")
	public String loginOk(LoginDTO dto, HttpSession session, RedirectAttributes rttr) throws Exception {
		
		// 사용자가 입력한아이디 값을 vo에 대입한다.
		MemberVO vo = memberService.login(dto.getMbsp_id());
		
		String msg = "";   //상태
 		String url = "/";  //메인주소
		
 		
 		if(vo != null) { //아이디가 존재하면?(비밀번호도 존재함) > 비밀번호 비교작업
		
 			//dto.getMbsp_password() : 사용자가 입력한 비번, vo.getMbsp_password() : db암호화된 비번
 			if(passwordEncoder.matches(dto.getMbsp_password(), vo.getMbsp_password())) {
 				vo.setMbsp_password(""); //사용자가 입력한 비번을 비워줌. 암호화 되어있지만 그래도 비밀번호니까 보안.
 				session.setAttribute("login_status", vo); //로그인 상태
 			}else {
 				msg = "failPW";  //비밀번호 틀리면
 				url = "/member/login";  //로그인 페이지로 다시 이동
 			}
 		}else {
 			msg ="failID";  //아이디가 틀리면
 			url = "/member/login";  //로그인 페이지로 다시 이동
 		}
 		
 		rttr.addFlashAttribute("msg", msg); //타임리프에서 사용할 1회성 데이터
 		
		log.info("url확인 "+ url);
		log.info("msg확인 : " + msg);
 		log.info("session확인" + session);
		
 		return "redirect:" + url; // 로그인 성공시 메인으로 이동
	}

	
	// [로그아웃]
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		//세션삭제
		session.invalidate();
		return "redirect:/";
	}

	
	//[아이디 찾기 폼]
	@GetMapping("/idfind")
	public void idfindForm() {
		log.info("idfindForm 실행");
	}
	
	
	//[아이디 찾기 작업]
	@PostMapping("/idfind")
	public String idfndOk(String mbsp_name, String mbsp_email, String authcode, HttpSession session, RedirectAttributes rttr) throws Exception {

		String url = "";
		String msg = "";
		
		//세션 살아있을 때, 사용자가 입력한 인증코드가 서버에서 보낸 인증코드와 일치한다면
		if(authcode.equals(session.getAttribute("authcode"))) {
			
			//아이디 찾기 기능으로 찾은 아이디를 mbsp_id에 대입한다.
			String mbsp_id = memberService.idfind(mbsp_name, mbsp_email);
		
			//만약 아이디가 null값이 아니면
			if(mbsp_id != null) {
				
				//메일발송작업 (메일에 작성될 요소들)
				String subject = "SmileMall 아이디를 보내드립니다.";
				EmailDTO dto = new EmailDTO("SmileMall", "SmileMall", mbsp_email, subject, mbsp_id);
				
				log.info("dto확인용 :"+ dto);
				
				emailService.sendMail("emailIDResult", dto, mbsp_id);
				
				//인증코드 세션을 소별시킨다.
				session.removeAttribute("authcode");
				
				//idfind.html에서 msg를 활용하여 성공메세지 출력 후 로그인 페이지로 이동
				msg = "success";
				url = "/member/login";
				rttr.addFlashAttribute("msg",msg);
			}else {
				// 실패시 idfind.html에서 msg 활용하여 실패 메세지 출력후 아이디 찾기 페이지 출력
				msg = "failName";
				url = "/member/idfind";
			}
		}else {
			// 인증코드 잘봇 입력 시 idfind.html에서 msg 활용하여 실패 메세지 출력후 아이디 찾기 페이지 출력
			msg = "failAuthcode";
			url = "/member/idfind";
		}
		rttr.addFlashAttribute("msg", msg);
		
		
		return "redirect:" + url; 
	}
	
	
}
