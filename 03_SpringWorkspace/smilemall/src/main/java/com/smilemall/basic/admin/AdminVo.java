package com.smilemall.basic.admin;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdminVo {
	// admin_tbl, admin_id, admin_pw, admin_visit_date

	private String  admin_id;			//관리자 아이디
	private String admin_pw;			//관리바 비밀번호
	private Date admin_visit_date;		//방문날짜




}
