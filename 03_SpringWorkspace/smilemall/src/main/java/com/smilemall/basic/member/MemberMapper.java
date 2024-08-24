package com.smilemall.basic.member;

import org.apache.ibatis.annotations.Param;

public interface MemberMapper {

	
	// [아이디 중복체크]
	String idCheck(String mbsp_id);
	
	// [닉네임 중복체크]
	String nickCheck(String mbsp_nick);	
	
	// [회원가입 저장]
	void join(MemberVo vo);
	
	//[로그인 작업] 
	MemberVo login(String mbsp_id);
	
	// [아이디 찾기]
	String idfind(@Param("mbsp_name") String mbsp_name, @Param("mbsp_email") String mbsp_email);
	
	// [비밀번호 찾기]
	String pwfind(@Param("mbsp_id") String mbsp_id, @Param("mbsp_name") String mbsp_name, @Param("mbsp_email") String mbsp_email);
	// [비밀번호 업데이트(재설정)]
	void tempPwUpdate(@Param("mbsp_id") String mbsp_id, @Param("temp_enc_pw") String temp_enc_pw);
	
	//[마이페이지 수정하기]
	void modify(MemberVo vo);
	
	// [비밀번호 변경 작업]
	void changePw(@Param("mbsp_id") String mbsp_id, @Param("new_mbsp_password") String new_mbsp_password);
	
	// [회원탈퇴]
	void delete(String mbsp_id);
	
	// [최근로그인]
	void last_login(String mbsp_id);
}
