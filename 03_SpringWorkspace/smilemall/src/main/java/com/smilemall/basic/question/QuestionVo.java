package com.smilemall.basic.question;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QuestionVo {
/*
qa_tbl
qa_code, mbsp_id, pro_num, qa_title, qa_content, qa_date, qa_admin_reply_content, qa_admin_reply_date, qa_admin_reply_status,
pro_name, pro_up_folder, pro_img
seq_qa_code
pk_qa_code
 */
	
	// qa_tbl
	private Long qa_code;					// 문의 번호
	private String mbsp_id;					// 문의한 사용자 아이디
	private int pro_num;					// 문의 상품 번호
	private String qa_title;				// 문의제목
	private String qa_content;				// 문의내용
	private Date qa_date;					// 문의날짜
	
	private String qa_admin_reply_content;	// 관리자 리뷰 답변
	private Date qa_admin_reply_date;		// 관리자 리뷰 답변 날짜
	private String qa_admin_reply_status;	// 관리자 리뷰답변 여부 
	
	// product_tbl
	private String pro_name;		// 상품명
	private String pro_up_folder;	//이미지업로드폴더
	private String pro_img;			//이미지 파일명
	
}
