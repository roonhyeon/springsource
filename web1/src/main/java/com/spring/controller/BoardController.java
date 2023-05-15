package com.spring.controller;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.BoardDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller // http://localhost:8080 => 요청 응답 컨트롤러
@RequestMapping("/board")
public class BoardController {
	
//	@RequestMapping(value ="/read", method=RequestMethod.GET)
	@GetMapping("/read")
	public void read() {
		log.info("read request");
	}
	
//	@RequestMapping(value ="/register", method=RequestMethod.GET)
	@GetMapping("/register")
	public void register() {
		log.info("register request");
	}
	
	// 1. Model 객체 == request.setAttribute()
	// 2. RedirectAttributes
	//    1) addAttribute(): 일회성으로 데이터를 전달 => 객체 상태로 값을 전달할 수 없다. 주소줄에 따라가는 방식
	//    2) addFlashAttribute(): 역시 일회성으로 데이터를 전달한다. 그러나 session을 사용하고 객체 상태로 전달이 가능하다.
    
	// HttpSession

	// read 로 이동
//	@PostMapping("/register")
//	public String registerPost(BoardDTO dto, RedirectAttributes rttr) {
//		log.info("board register request "+dto);
//		
//		// 등록 버튼을 눌렀을 때 입력칸마다 BoardDTO 값이 유지가 되려면 forward로 움직여야 한다.
//		// BoardDTO 객체에 담긴 값을 read.jsp에서 사용하고 싶다면?
//		
//		// addAttribute 사용 시 주소줄에 'board/read?name=홍길동&password=1234&title=스프링&content=스프링' 입력값이 따라간다.
////		rttr.addAttribute("name", dto.getName());
////		rttr.addAttribute("password", dto.getPassword());
////		rttr.addAttribute("title", dto.getTitle());
////		rttr.addAttribute("content", dto.getContent());
//		
//		// addFlashAttribute 사용 시 주소줄에 입력값 따라가지 않는다. 그리고 입력칸에서 값도 유지가 된다.
//		rttr.addFlashAttribute("boardDTO", dto);
//		
//		return "redirect:/board/read";
//	}
	
	// @ModelAttribute("dto"): 괄호 안은 생락이 가능하다. 이것의 역할은 Model 객체(model.addAttribute()) 대신 사용하는 것이다. 도메인 객체에 담을 때 "이름"을 지정한다. 
	// => registerPost(BoardDTO dto): jsp 페이지에서 값을 가져올 때 ${boardDTO.name} 이렇게 할 수 있다면,
	// => registerPost(@ModelAttribute("dto") BoardDTO dto): jsp 페이지에서 값을 가져올 때 ${dto.name}으로 가져온다. 그리고 입력한 값이 유지된다.
	
	@PostMapping("/register")
	public void registerPost(@ModelAttribute("dto") BoardDTO dto, RedirectAttributes rttr) {
		log.info("board register request "+dto);
		
		// 등록 버튼을 눌렀을 때 입력칸마다 BoardDTO 값이 유지가 되려면 forward로 움직여야 한다.
		
	}
	
//	@RequestMapping(value ="/modify", method=RequestMethod.GET)
	@GetMapping("/modify")
	public void modify() {
		log.info("modify request");
	}
	
//	@RequestMapping(value ="/remove", method=RequestMethod.GET)
	@GetMapping("/remove")
	public void remove() {
		log.info("remove request");
	}
	
}
