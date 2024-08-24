package com.smilemall.basic.member;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberVo {
/*
mbsp_tbl
mbsp_id, mbsp_name, mbsp_email, mbsp_password, mbsp_zipcode, mbsp_addr, mbsp_deaddr, mbsp_phone,
mbsp_nick, mbsp_receive, mbsp_point, mbsp_lastlogin, mbsp_datesub, mbsp_updatedate
pk_mbsp_id
 */
	
	private String mbsp_id;			// 1.아이디
	private String mbsp_name;		// 2.이름
	private String mbsp_email;		// 3.이메일
	private String mbsp_password;	// 4.비밀번호
	private String mbsp_zipcode;	// 5.우편번호
	private String mbsp_addr;		// 6.주소
	private String mbsp_deaddr;		// 7.세부주소
	private String mbsp_phone;		// 8.연락처
	private String mbsp_nick;		// 9.닉네임
	private String mbsp_receive;	// 10.마케팅 수신여부
	private int mbsp_point;			// 11.포인트(사용자 입력정보x)
	private Date mbsp_lastlogin;	// 12.최근로그인(사용자 입력정보x)
	private Date mbsp_datesub;		// 13.최초등록일(사용자 입력정보x)
	private Date mbsp_updatedate;	// 14.마지막 정보갱신일(사용자 입력정보x)
	
}
