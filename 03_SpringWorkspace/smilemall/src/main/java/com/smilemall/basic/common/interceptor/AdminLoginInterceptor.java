package com.smilemall.basic.common.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.smilemall.basic.admin.AdminVo;
import com.smilemall.basic.member.MemberVo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AdminLoginInterceptor implements HandlerInterceptor {

	// [로그인 상태 확인]
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("AdminPreHandle 메서드 실행");
		
		boolean result = false;
		
		HttpSession session = request.getSession();
		AdminVo vo = (AdminVo)session.getAttribute("admin_state");
		
		//로그인 상태가 아닐때
		if(vo == null) {
			
			result = false;
			
				// ajax요청일 경우
				if(isAjaxRequest(request)) {
					
					log.info("ajax 요청");
					
					String refererUrl = request.getHeader("Referer");
					if(refererUrl != null) {
						log.info("refererUrl값: " + refererUrl);
						session.setAttribute("targetUrl", refererUrl);
					}
					response.sendError(400, "로그인이 필요합니다.");
				} else {
					// 원래 접속하려 했던 페이지url
					String targetUrl =getTargetUrl(request);		
					log.info("로그인 인터셉트 targetUrl 값:" + targetUrl);
					session.setAttribute("targetUrl", targetUrl);
			
					response.sendRedirect("/admin/"); // 로그인 페이지 출력
				}
				
		} else {
			result = true;
		}
		return result;	
	}
	
	
	// 접속하려 했던 페이지 url
	private String getTargetUrl(HttpServletRequest request) {
		
		String uri = request.getRequestURI();
		String query = request.getQueryString();
		
		if (query == null || query.equals("null")) {
			query = "";
		}else {
			query = "?" + query;
		}
		
		String targetUrl = uri + query;
		
		log.info("targetUrl값: " + targetUrl);
		
		//get요청일 때만 세션저장.
		if(request.getMethod().equals("GET")) {
			request.getSession().setAttribute("targetUrl", targetUrl);
		}
		return targetUrl;
	}
	
	// ajax요청 확인
	private boolean isAjaxRequest(HttpServletRequest request) {
		boolean isAjax = false;
		
		String header = request.getHeader("AJAX");
		
		log.info("header값:" + header);
		
		//header에 값이 있다. 그리고 header
		if(header != null && header.equals("true")) {
			log.info("ajax_true");
			isAjax = true;
		}

		return isAjax;
	
	}
	
	
	
}
