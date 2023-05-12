package com.spring.controller;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller // http://localhost:8080 => 요청 응답 컨트롤러
@RequestMapping("/board")
public class BoardController {
	
//	@RequestMapping(value ="/read", method=RequestMethod.GET)
	@GetMapping("/read")
	public void read() {
		log.info("read");
	}
	
//	@RequestMapping(value ="/register", method=RequestMethod.GET)
	@GetMapping("/register")
	public void register() {
		log.info("register");
	}
	
//	@RequestMapping(value ="/modify", method=RequestMethod.GET)
	@GetMapping("/modify")
	public void modify() {
		log.info("modify");
	}
	
//	@RequestMapping(value ="/remove", method=RequestMethod.GET)
	@GetMapping("/remove")
	public void remove() {
		log.info("remove");
	}
}
