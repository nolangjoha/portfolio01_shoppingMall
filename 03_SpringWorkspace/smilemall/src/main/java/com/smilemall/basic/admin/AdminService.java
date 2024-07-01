package com.smilemall.basic.admin;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {

	
	private final AdminMapper adminMapper;
	
	
	// [관리자 로그인]
	public AdminVo loginOk(String admin_id) {
		return adminMapper.loginOk(admin_id);
	}
	
	
}
