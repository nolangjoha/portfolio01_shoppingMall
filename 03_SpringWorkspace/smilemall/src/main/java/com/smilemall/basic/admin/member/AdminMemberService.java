package com.smilemall.basic.admin.member;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smilemall.basic.common.dto.Criteria;
import com.smilemall.basic.member.MemberVo;

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
	
}
