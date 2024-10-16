package com.smilemall.basic.common.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.smilemall.basic.common.interceptor.AdminLoginInterceptor;
import com.smilemall.basic.common.interceptor.LoginInterceptor;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WebMvcAdminConfig implements WebMvcConfigurer {

	private final AdminLoginInterceptor adminLoginInterceptor;
	
	
	private static final String[] EXCLUDE_PATHS = {
			"/admin/",
			"/admin/admin_ok",
			"/admin/category/secondcategory/*",
			"/admin/carousel/*",
			"/admin/product/image_display",
			"/admin/product/display/*"
	};
	
	
	@Override
	public void addInterceptors(InterceptorRegistry rigistry) {
		
		rigistry.addInterceptor(adminLoginInterceptor)
				.addPathPatterns("/admin/**")
				.excludePathPatterns(EXCLUDE_PATHS);
	}
	
	
}
