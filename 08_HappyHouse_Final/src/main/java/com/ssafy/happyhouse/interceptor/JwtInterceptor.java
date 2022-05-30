package com.ssafy.happyhouse.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.happyhouse.util.JwtUtil;

@Component
public class JwtInterceptor implements HandlerInterceptor {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// preflight를 위한 OPTIONS 요청은 그냥 전달
		if(request.getMethod().contentEquals("OPTIONS")) {
			return true;
		}
		
		// request의 헤더에서 jwt-auth-token으로 넘어온 녀석을 찾아본다.
		String authToken = request.getHeader("access-token");
		logger.debug("경로: {}, 토큰: {}", request.getServletPath(), authToken);
		
		if (authToken != null) {
			// 유효한 토큰이면 진행, 그렇지 않으면 예외를 발생시킨다.
			jwtUtil.checkAndGetClaims(authToken);
			return true;
		} else {
			throw new RuntimeException("인증 토큰이 없습니다.");
		}
	}
}
