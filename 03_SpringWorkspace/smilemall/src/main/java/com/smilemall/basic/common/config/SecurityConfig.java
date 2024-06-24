package com.smilemall.basic.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// 시큐리티 라이브러리를 사용하기 위한 설정작업을 위한 클래스
@Configuration
public class SecurityConfig {
		
		//암호화 빈생성
		@Bean
		PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}
}
