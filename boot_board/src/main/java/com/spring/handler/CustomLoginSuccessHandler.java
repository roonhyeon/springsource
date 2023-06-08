package com.spring.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

// 원래는 로그인 성공 후에 시작 페이지로 돌아가는데....(default)
// 로그인 성공 후 특정 작업을 추가하고 싶거나 이동해야 하는 경로를 지정하고 싶을 때 사용하는 옵션 클래스
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		List<String> roleNames=new ArrayList<String>();
		// 로그인 사용자의 권한 확인
		authentication.getAuthorities().forEach(auth -> roleNames.add(auth.getAuthority()));
		
		// 로그인 성공 시 ROLE_ADMIN 이라면 adminpage로 이동, ROLE_USER 라면 userpage 이동
		if(roleNames.contains("ROLE_ADMIN")) {
			response.sendRedirect("/member/admin");
			return;
		}
		
		if(roleNames.contains("ROLE_MEMBER") || roleNames.contains("ROLE_MANAGER")) {
			response.sendRedirect("/board/list");
			return;
		}		
		
		response.sendRedirect("/");
		
	}

}
