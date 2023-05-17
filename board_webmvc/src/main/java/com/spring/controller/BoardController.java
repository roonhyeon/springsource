package com.spring.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.domain.BoardDTO;
import com.spring.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/board")
@Slf4j
@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	// 전체 리스트 보여주기 컨트롤러 작성: list.jsp 보여주기
	@GetMapping("/list")
	public void getList(Model model) {
		log.info("list request");
		
		List<BoardDTO> list=service.getList();
		model.addAttribute("list", list);
	}
	
	// register.jsp 보여주기
	@GetMapping("/register")
	public void registerForm() {
		log.info("register form request");
	}
	
	@PostMapping("/register")
	public String register(BoardDTO boardDTO) {
		log.info("register request "+boardDTO);
		
		boolean insertFlag=service.register(boardDTO);
		if(insertFlag) {
			return "redirect:/board/list";
		} else {
			return "/board/register";
		}
	}
	
	@GetMapping("/read")
	public void readGet(int bno) {
		log.info("read request "+bno);
	}

}
