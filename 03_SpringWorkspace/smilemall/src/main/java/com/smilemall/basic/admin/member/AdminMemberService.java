package com.smilemall.basic.admin.member;

import java.util.List;


import org.springframework.stereotype.Service;

import com.smilemall.basic.common.dto.Criteria;
import com.smilemall.basic.member.MemberVo;
import com.smilemall.basic.order.OrderVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminMemberService {

	private final AdminMemberMapper adminMemberMapper;
	
	// [회원목록]
	public List<MemberVo> member_list(Criteria cri) {
		return adminMemberMapper.member_list(cri);
	}
	
	// [전체회원 데이터 수]
	public int getTotalCount(Criteria cri) {
		return adminMemberMapper.getTotalCount(cri);
	}

	// [회원상세정보]
	public MemberVo member_info(String mbsp_id) {
		return adminMemberMapper.member_info(mbsp_id);
	}
	
	// [회원상세정보 수정하기]
	public void member_modify(MemberVo vo) {
		adminMemberMapper.member_modify(vo);
	}

	// [회원정보 삭제하기]
	public void member_delete(String mbsp_id) {
		adminMemberMapper.member_delete(mbsp_id);
	}

	// [회원정보 일괄삭제]
	public void member_checked_delete(List<String> mbsp_id) {
		adminMemberMapper.member_checked_delete(mbsp_id);
	}
	
	// [회원주문정보목록]
	public List<OrderVo> member_ord_info_list(String mbsp_id) {
		return adminMemberMapper.member_ord_info_list(mbsp_id);
	}
	
	// [회원주문정보목록: 총데이터 개수]
	public int getTotalCountOrderList(String mbsp_id) {
		return adminMemberMapper.getTotalCountOrderList(mbsp_id);
	}
	
	// [회원주문정보목록: 총주문금액]
	public int OrderPriceTotal(String mbsp_id) {
		return adminMemberMapper.OrderPriceTotal(mbsp_id);
	}
	
	// [메일저장]
	public void mailing_save(MailingVo vo) {
		adminMemberMapper.mailing_save(vo);
	}
	
	// [메일목록]
	public List<MailingVo> mailing_list(Criteria cri) {
		return adminMemberMapper.mailing_list(cri);
	}
		
	// [메일목록 총개수]
	public int getMailingTotalCount(Criteria cri) {
		return adminMemberMapper.getMailingTotalCount(cri);
	}
	
	// [저장메일 정보]
	public MailingVo mailing_save_info(int mail_idx) {
		return adminMemberMapper.mailing_save_info(mail_idx);
	}
	
	// [저장메일 수정]
	public void mailing_modify(MailingVo vo) {
		adminMemberMapper.mailing_modify(vo);
	}
	
	// [저장메일 삭제]
	public void mailing_delete(int mail_idx) {
		adminMemberMapper.mailing_delete(mail_idx);
	}
	
	//[마케팅 수신동의 회원목록 메일정보 읽어오기]
	public String[] all_mail_address() {
		return adminMemberMapper.all_mail_address();
	}
	
	// [메일발송 횟수 업데이트]
	public void mailSendCountUpdate(int mail_send_count) {
		adminMemberMapper.mailSendCountUpdate(mail_send_count);
	}
	

}
