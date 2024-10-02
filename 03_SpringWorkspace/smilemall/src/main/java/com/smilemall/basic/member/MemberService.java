package com.smilemall.basic.member;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.smilemall.basic.common.dto.Criteria;
import com.smilemall.basic.question.QuestionVo;
import com.smilemall.basic.review.ReviewVo;

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
	public void join(MemberVo vo) {
		memberMapper.join(vo);
	}
		
	//[로그인 작업]
	public MemberVo login(String mbsp_id) {
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
	public void modify(MemberVo vo) {
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
	
	// [최근로그인]
	public void last_login(String mbsp_id) {
		memberMapper.last_login(mbsp_id);
	}
	
	
	// [상품리뷰목록]
	public List<ReviewVo> rev_list(Criteria cri, String mbsp_id, String reply_status, String start_date, String end_date){
		return memberMapper.rev_list(cri, mbsp_id, reply_status, start_date, end_date);
	}
		
	// [상품리뷰 전체데이터개수]
	public int getTotalReviewCount(Criteria cri, String mbsp_id, String reply_status, String start_date, String end_date) {
		return memberMapper.getTotalReviewCount(cri, mbsp_id, reply_status, start_date, end_date);
	}
	
	// [리뷰상세보기]
	public ReviewVo review_more(Long re_code,  String mbsp_id) {
		return memberMapper.review_more(re_code, mbsp_id);
	}	
	
	
		
	// [상품문의목록]
	List<QuestionVo> question_list(Criteria cri, String mbsp_id, String reply_status, String start_date, String end_date) {
		return memberMapper.question_list(cri, mbsp_id, reply_status, start_date, end_date);
	}

	// [문의 전체 데이터 개수]
	int getTotalQuestionCount(Criteria cri, String mbsp_id, String reply_status, String start_date, String end_date) {
		return memberMapper.getTotalQuestionCount(cri, mbsp_id, reply_status, start_date, end_date);
	}
	
	// [문의상세보기]
	QuestionVo question_more(Long qa_code, String mbsp_id) {
		return memberMapper.question_more(qa_code, mbsp_id);
	}
	
	
	
	// [주문목록]
	List<MemberOrderVo> ord_list(Criteria cri, String mbsp_id, String p_status, String start_date, String end_date) {
		return memberMapper.ord_list(cri, mbsp_id,p_status, start_date, end_date);
	}
	
	//[주문목록 전체 갯수]
	int getTotalOrderCount(Criteria cri, String mbsp_id,String p_status, String start_date, String end_date) {
		return memberMapper.getTotalOrderCount(cri, mbsp_id, p_status,start_date, end_date);
	}
	
	
	
	
}
