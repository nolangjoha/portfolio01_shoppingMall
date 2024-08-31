package com.smilemall.basic.admin.member;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smilemall.basic.common.dto.Criteria;
import com.smilemall.basic.member.MemberVo;
import com.smilemall.basic.order.OrderVo;

public interface AdminMemberMapper {

	// [회원목록]
	List<MemberVo> member_list(Criteria cri);
	
	// [전체회원 데이터 수]
	int getTotalCount(Criteria cri);
	
	// [회원상세정보]
	MemberVo member_info(String mbsp_id);
	
	// [회원상세정보 수정하기]
	void member_modify(MemberVo vo);
	
	// [회원정보 삭제하기]
	void member_delete(String mbsp_id);
	
	// [회원정보 일괄삭제]
	void member_checked_delete(List<String> mbsp_id);
	
	// [회원주문정보목록]
	List<OrderVo> member_ord_info_list(String mbsp_id);
		
	// [회원주문정보목록: 총데이터 개수]
	int getTotalCountOrderList(String mbsp_id);
	
	// [회원주문정보목록: 총주문금액]
	Integer OrderPriceTotal(String mbsp_id);
	
	// [메일저장]
	void mailing_save(MailingVo vo);

	// [메일목록]
	List<MailingVo> mailing_list(Criteria cri);

	// [메일목록 총개수]
	int getMailingTotalCount(Criteria cri);
	
	// [저장메일 정보]
	MailingVo mailing_save_info(int mail_idx);
	
	// [저장메일 수정]
	void mailing_modify(MailingVo vo);
	
	// [저장메일 삭제]
	void mailing_delete(int mail_idx);
	
	// [마케팅 수신동의 회원목록 메일정보 읽어오기]
	String[] all_mail_address();
	
	// [메일발송 횟수 업데이트]
	void mailSendCountUpdate(int mail_send_count);
	

	
	
}
