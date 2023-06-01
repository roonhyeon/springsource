package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/security")
public class SecurityController {
	
	@GetMapping("/login")
	public void loginGet() {
		log.info("login form request ");
	}
	
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

}
