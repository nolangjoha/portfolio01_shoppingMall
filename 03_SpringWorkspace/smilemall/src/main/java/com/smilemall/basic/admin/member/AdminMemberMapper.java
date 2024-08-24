package com.smilemall.basic.admin.member;

import java.util.List;

import com.smilemall.basic.common.dto.Criteria;
import com.smilemall.basic.member.MemberVo;

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
	
	
}
