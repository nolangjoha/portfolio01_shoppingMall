package com.smilemall.basic.common.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import lombok.extern.slf4j.Slf4j;

// 이메일 환경설정(email.properties) 인식시켜주는 클래스

@Slf4j
@Configuration
@PropertySource("classpath:mail/email.properties") // 이메일 환경설정 파일 위치
public class EmailConfig {

	public EmailConfig() throws Exception{
		log.info("EmailConfig 생성자 called");
	}
	
	//현재 사용되지 않는듯 하나 영향을 주는것 같으므로 작성
	@Value("${spring.mail.transport.protocol}")
	private String protocol;
	@Value("${spring.mail.debug}")
	private boolean debug;
	
	
	
	@Value("${spring.mail.properties.mail.smtp.auth}")
	private boolean auth;
	
	@Value("${spring.mail.properties.mail.smtp.starttls.enable}")
	private boolean starttls;
	
	@Value("${spring.mail.host}")
	private String host;
	
	@Value("${spring.mail.port}")
	private int port;
	
	@Value("${spring.mail.username}")
	private String username;
	
	@Value("${spring.mail.password}")
	private String password;
	
	@Value("${spring.mail.default-encoding}")
	private String encoding;
	
	
	//메일발송 객체 주입작업
	@Bean
	public JavaMailSender javaMailSender() {
	
		// 어떤 메일서버를 이용해 메일발송을 할지 서버에 대한 정보를 구성하는 작업
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		
		Properties properties = new Properties();

		properties.put("mail.smtp.auth", auth);
		properties.put("mail.smtp.starttls.enable", starttls);

		mailSender.setHost(host);
		mailSender.setUsername(username);
		mailSender.setPassword(password);
		mailSender.setPort(port);
		mailSender.setJavaMailProperties(properties);
		mailSender.setDefaultEncoding(encoding);
		
		System.out.println("메일서버:" + host);
		log.info("메일서버로그:" + host);
		
		return mailSender;
	
	}
	
	
	
	
}
