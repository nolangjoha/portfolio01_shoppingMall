package com.smilemall.basic.member;

public interface MemberMapper {

	
	// [아이디 중복체크]
	String idCheck(String mbsp_id);
	
	// [닉네임 중복체크]
	String nickCheck(String mbsp_nick);	
	
	
}
