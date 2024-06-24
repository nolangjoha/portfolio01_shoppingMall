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
		return memberMapper.nickCheck(mbsp_nick);
	}
	
	//[회원가입 저장]
	public void join(MemberVO vo) {
		memberMapper.join(vo);
	}
		
	//[로그인 작업]
	public MemberVO login(String mbsp_id) {
		return memberMapper.login(mbsp_id);
	}
	
	//[아이디 찾기]
	public String idfind(String mbsp_name, String mbsp_email) {
		return memberMapper.idfind(mbsp_name, mbsp_email);
	}
	
	
}
