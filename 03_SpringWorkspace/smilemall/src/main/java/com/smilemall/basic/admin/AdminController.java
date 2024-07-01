package com.smilemall.basic.admin;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/*")
public class AdminController {

	private final AdminService adminService;
	
	private final PasswordEncoder passwordEncoder;
	
	// [관리자 로그인 페이지]
	@GetMapping("")
	public String loginForm() {
		return "/admin/adlogin";
	}
	
	// [관리자 메뉴 페이지]
	@GetMapping("/admin_menu")
	public void admin_menu() {
	}
	
	
	// [관리자 로그인 기능]
	@PostMapping("/admin_ok")
	public String admin_ok(AdminVo vo, HttpSession session) throws Exception {
		
		//사용자가 입력한 아이디를 대입한다.
		AdminVo d_vo = adminService.loginOk(vo.getAdmin_id());
		
		String url = "";
		
		//사용자의 입력값이 DB에 있다면
		if(d_vo != null) {
			
			// DB에 저장된 비밀번호와 사용자가 입력한 비밀번호가 일치한다면
			if(passwordEncoder.matches(vo.getAdmin_pw(), d_vo.getAdmin_pw())) {
				
				//비밀번호니까 일단 지워준다.
				d_vo.setAdmin_pw("");
				// 사용자의 아이디에 세션값 부여
				session.setAttribute("admin_state", d_vo);
				
				url = "admin/admin_menu";
			}
		}
		return "redirect:/" + url;
	}
	
	
	// [관리자 페이지 로그아웃]
	@GetMapping("/admin_logout")
	public String admin_logout(HttpSession session) {
		
		//관리자 세션부분만 제거
		session.removeAttribute("admin_state");
		
		return "redirect:/admin/";
	}
	
	
}
