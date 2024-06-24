package com.smilemall.basic.member;

import org.apache.ibatis.annotations.Param;

public interface MemberMapper {

	
	// [아이디 중복체크]
	String idCheck(String mbsp_id);
	
	// [닉네임 중복체크]
	String nickCheck(String mbsp_nick);	
	
	// [회원가입 저장]
	public void join(MemberVO vo);
	
	//[로그인 작업] 
	MemberVO login(String mbsp_id);
	
	// [아이디 찾기]
	String idfind(@Param("mbsp_name") String mbsp_name, @Param("mbsp_email") String mbsp_email);
	
	
	
}
