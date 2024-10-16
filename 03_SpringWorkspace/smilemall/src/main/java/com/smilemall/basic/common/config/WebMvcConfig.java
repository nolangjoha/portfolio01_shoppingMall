package com.smilemall.basic.common.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.smilemall.basic.common.interceptor.LoginInterceptor;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

	private final LoginInterceptor loginInterceptor;
	
	private static final String[] EXCLUDE_PATHS = {
			"/member/join",
			"/member/idCheck",
			"/member/nickCheck",
			"/member/login",
			"/member/logout",
			"/member/idfind",
			"/member/pwfind"
	};
	
	@Override
	public void addInterceptors(InterceptorRegistry rigistry) {
		
		rigistry.addInterceptor(loginInterceptor)
				.addPathPatterns(
								"/member/**",
								"/cart/**",
								"/order/**"
						)
				.excludePathPatterns(EXCLUDE_PATHS);
		
	}
	
	
}
