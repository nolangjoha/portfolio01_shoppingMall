package com.smilemall.basic.review;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewVo {
/*
-- review_tbl(re_code, mbsp_id, pro_num, re_title, re_content, re_rate, re_date, 
   re_admin_reply_content, re_admin_reply_date, re_admin_reply_status, pro_name)
-- SEQ_REVIEW_CODE
-- PK_RE_CODE 
 */
	
	private Long re_code;
	private String mbsp_id;
	private int pro_num;
	private String re_title;
	private String re_content;
	private int re_rate;
	private Date re_date;
	
	private String re_admin_reply_content;	// 관리자 리뷰 답변
	private Date re_admin_reply_date;		// 관리자 리뷰 답변 날짜
	private String re_admin_reply_status;	// 관리자 리뷰답변 여부 
	
	private String pro_name;				// 상품명
	private String pro_up_folder;	//이미지업로드폴더
	private String pro_img;			//이미지 파일명
}
