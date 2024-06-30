package com.smilemall.basic.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/*")
public class AdminController {

	private final AdminService adminService;
	
	// [관리자 로그인 페이지]
	@GetMapping("")
	public String loginForm() {
		return "/admin/adlogin";
	}
	
	// [관리자 메뉴 페이지]
	@GetMapping("/admin_menu")
	public void admin_menu() {
		
	}
	
	
}
