package com.smilemall.basic.mail;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.smilemall.basic.common.constants.Constants;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

	private final JavaMailSender mailsender;
	
	private final SpringTemplateEngine templateEngine;
	
	
	public void sendMail(String type, EmailDTO dto, String authcode) {
		
		// mail/파일이름
		type = Constants.MAILFORDERNAME + "/" + type;
		
		//메일구성정보 담당 객체 : (받는사람, 보내는사람, 받는사람 메일주소, 본문내용)
		MimeMessage mimeMessage = mailsender.createMimeMessage();
		
		try {
			// 메일템플릿 사용목작
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
			mimeMessageHelper.setTo(dto.getReceiverMail());  //메일 수신자
			mimeMessageHelper.setFrom(new InternetAddress(dto.getSenderMail(), dto.getSenderName())); //메일 발신자
			mimeMessageHelper.setSubject(dto.getSubject()); //메일 제목
			mimeMessageHelper.setText(setContext(authcode, type), true); //메일본문내용
			
			//메일 발송 기능
			mailsender.send(mimeMessage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public String setContext(String authcode, String type) {
		Context context = new Context();
		context.setVariable("authcode", authcode);
		return templateEngine.process(type, context);
	}
	
	
}
