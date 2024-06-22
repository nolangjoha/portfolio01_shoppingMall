package com.smilemall.basic.member;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberMapper memberMapper;
	
	//[아이디 중복체크]
	public String idCheck(String mbsp_id) {
		return memberMapper.idCheck(mbsp_id);
	}
	
	//[닉네임 중복체크]
	public String nickCheck(String mbsp_nick) {
		return memberMapper.idCheck(mbsp_nick);
	}
}
