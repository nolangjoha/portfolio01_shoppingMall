package com.smilemall.basic.member;

import java.util.UUID;

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
	
	
	// [비밀번호 찾기]
	public String pwfind(String mbsp_id,  String mbsp_name, String mbsp_email) {
		return memberMapper.pwfind(mbsp_id, mbsp_name, mbsp_email);
	}
	//[비밀번호 업데이트(재설정)]
	public void tempPwUpdate(String mbsp_id, String temp_enc_pw) {
		memberMapper.tempPwUpdate(mbsp_id, temp_enc_pw);
	}
	// <임시비밀번호 10자리>
	public String getTemPw() {
		return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
	}

	//[수정하기]
	public void modify(MemberVO vo) {
		memberMapper.modify(vo);
	}	

	
	// [비밀번호 변경하기]
	public void changePw(String mbsp_id, String new_mbsp_password) {
		memberMapper.changePw(mbsp_id, new_mbsp_password);
	}
	
	
	// [회원탈퇴]
	public void delete(String mbsp_id) {
		memberMapper.delete(mbsp_id);
	}
	
	
	
	
}
