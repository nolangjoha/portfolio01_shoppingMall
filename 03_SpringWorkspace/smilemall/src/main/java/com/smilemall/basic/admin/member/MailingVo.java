package com.smilemall.basic.admin.member;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MailingVo {
/*
mailing_tbl
mail_idx, mail_title, mail_content, mail_kind, mail_send_count, reg_date
pk_mailing_idx
seq_mailing_tbl
*/
	
	private Integer mail_idx;		//메일번호
	private String mail_title;		//메일제목
	private String mail_content;	//메일내용
	private String mail_kind;		//메일종류
	private int mail_send_count;	//메일발송횟수
	private Date reg_date;			//메일발송날짜
}
