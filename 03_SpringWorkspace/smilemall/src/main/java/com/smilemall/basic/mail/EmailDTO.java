package com.smilemall.basic.mail;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
@AllArgsConstructor 
public class EmailDTO {

	private String senderName;  	//발신자
	private	String senderMail;		//발신자 메일주소
	private String receiverMail;	//수신자 메일주소
	private String subject; 		//제목
	private String message;			//내용
	
	public EmailDTO() {
		this.senderMail = "SmileMall";
		this.senderMail = "SmileMall";
		this.subject  = "SmileMall 회원가입 매일 인증코드 입니다.";
		this.message = "메일 인증코드를 확인하시고, 회원가입시 인증코드 입력란에 입력 바랍니다.";
	
	
	}
	
}
