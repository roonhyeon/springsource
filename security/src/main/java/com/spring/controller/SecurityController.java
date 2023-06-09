package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.domain.SpUserDTO;
import com.spring.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/security")
public class SecurityController {
	
	@Autowired
	private MemberService service;
	
	// 회원가입
	@GetMapping("/register")
	public void registerGet() {
		log.info("register form request");
	}
	
	// 회원가입 성공 시 index 이동, 실패 시 register 이동
	@PostMapping("/register")
	public String registerPost(SpUserDTO dto) {
		log.info("register request "+dto);
		
		String path=service.register(dto) ? "redirect:/" : "/security/register";
		return path;
	}
	
	@GetMapping("/login")
	public void loginGet() {
		log.info("login form request ");
	}
	
	// hasAnyAuthority(): security-cotext.xml의 intercept-url과 같은 역할. 가로 안에 정의된 해당 권한을 가지고 있니? 라는 의미다.
	@PreAuthorize("hasAnyAuthority('ROLE_USER')")  
	@GetMapping("/userpage")
	public void userPage() {
		log.info("user page request ");
	}
	
	@GetMapping("/adminpage")
	public void adminPage() {
		log.info("admin page request ");
	}
	
	@GetMapping("/login-error") // 비밀번호 틀렸을 때
	public String loginError(Model model) {
		model.addAttribute("error", "아이디나 비밀번호를 확인해주세요.");
		return "/security/login";
	}
	
	@GetMapping("/access-denied")
	public String accessDenied() {
		return "/security/denied";
	}

	// 로그인한 사람의 권한을 보여준다.
	@GetMapping("/auth")
	@ResponseBody
	public Authentication auth() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

}
