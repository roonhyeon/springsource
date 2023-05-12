package com.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller // http://localhost:8080 => 요청 응답 컨트롤러
@RequestMapping("/member")
public class MemberController {
	 
	// @RequestMapping을 하면 get + post 둘다 응답
	//@RequestMapping("/login") // http://localhost:8080/member/login
	
	// 사용자 입력값 가져오기
	// 1) HttpServletRequest 사용하기
	// 2) 변수 사용: 변수명은 폼 태그 name과 일치해야 한다. 만약 일치하지 않는다면 @RequestParam을 사용한다.
	// 3) 바인딩 객체 사용
	
	
//	@RequestMapping(value="/login", method=RequestMethod.GET)
	@GetMapping("/login")
	public void loginGet(HttpServletRequest req) { // 클래스를 void로 바꿔주면 return 코드를 안 써줘도 된다.(주소창 경로와 jsp 파일이 들어있는 폴더의 경로가 서로 같을 때만)
		log.info("login...");
		log.info("method "+req.getMethod());
		//return "/member/login"; // 리턴이 있다면 viewResolver가 /WEB-INF/views/login.jsp
	}
	
//	@RequestMapping(value="/login", method=RequestMethod.POST)
//	@PostMapping("/login")
//	public void loginPost(HttpServletRequest req) {
//		log.info("login post...");
//		log.info("method "+req.getMethod());
//		// 사용자 입력값 id, password 가져오기
//		System.out.println("id "+req.getParameter("id"));
//		System.out.println("password "+req.getParameter("password"));
//	}
	
	@PostMapping("/login")
	public void loginPost(@RequestParam("userid") String id, String password) {
		log.info("login post...");
		// 사용자 입력값 id, password 가져오기
		System.out.println("id "+id);
		System.out.println("password "+password);
	}
	
//	@RequestMapping("/register") // http://localhost:8080/member/register
	@GetMapping("/register")
	public void registerGet() {
		log.info("register...");
		//return "/member/register"; // 리턴이 있다면 viewResolver가 /WEB-INF/views/register.jsp
	}

}
