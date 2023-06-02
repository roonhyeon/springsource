package com.spring.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		log.info("접근 제한 핸들러............."); // user1으로 로그인을 하고 관리자 페이지에 접속하려고 하면 콘솔창에 뜨는 메시지
		
		// response 객체에 정보를 담거나 에러 페이지로 이동
		response.sendRedirect("/security/access-denied");

	}

}
