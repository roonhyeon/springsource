package com.spring.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

	@GetMapping("/login")
	public void loginGet() {
		log.info("login form request");
	}

	@GetMapping("/login-error")
	public String loginError(Model model) {

		model.addAttribute("error", "아이디나 비밀번호를 확인해주세요.");

		return "/member/login";
	}

	@GetMapping("/admin")
	public void adminGet() {
		log.info("admin request");
	}

	// 로그인한 사람의 권한을 보여준다.
	@GetMapping("/auth")
	@ResponseBody
	public Authentication auth() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
}
