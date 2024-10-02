package com.smilemall.basic.member;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smilemall.basic.common.dto.Criteria;
import com.smilemall.basic.order.OrderVo;
import com.smilemall.basic.question.QuestionVo;
import com.smilemall.basic.review.ReviewVo;

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
	
	
	List<ReviewVo> rev_list(@Param("cri") Criteria cri, @Param("mbsp_id") String mbsp_id,  @Param("reply_status") String reply_status,
			@Param("start_date") String start_date, @Param("end_date") String end_date);

	// [상품리뷰 전체데이터개수]
	int getTotalReviewCount(@Param("cri") Criteria cri, @Param("mbsp_id") String mbsp_id,  @Param("reply_status") String reply_status,
			@Param("start_date") String start_date, @Param("end_date") String end_date);

	// [리뷰상세보기]
	ReviewVo review_more(@Param("re_code") Long re_code, @Param("mbsp_id") String mbsp_id);
	
	
	
	// [상품문의목록]
	List<QuestionVo> question_list(@Param("cri") Criteria cri, @Param("mbsp_id") String mbsp_id, @Param("reply_status") String reply_status,
			  					   @Param("start_date") String start_date, @Param("end_date") String end_date);
	
	// [문의 전체 데이터 개수]
	int getTotalQuestionCount(@Param("cri") Criteria cri, @Param("mbsp_id") String mbsp_id,@Param("reply_status") String reply_status, 
							  @Param("start_date") String start_date, @Param("end_date") String end_date);
	
	// [문의상세보기]
	QuestionVo question_more(@Param("qa_code") Long qa_code, @Param("mbsp_id") String mbsp_id);


	
	// [주문목록]
	List<MemberOrderVo> ord_list(@Param("cri") Criteria cri, @Param("mbsp_id") String mbsp_id,@Param("p_status") String p_status, @Param("start_date") String start_date, @Param("end_date") String end_date);
	
	//[주문목록 전체 갯수]
	int getTotalOrderCount(@Param("cri") Criteria cri, @Param("mbsp_id") String mbsp_id, @Param("p_status") String p_status, @Param("start_date") String start_date, @Param("end_date") String end_date);
	
	
}
