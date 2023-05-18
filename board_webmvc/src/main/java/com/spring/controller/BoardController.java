package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.BoardDTO;
import com.spring.domain.Criteria;
import com.spring.domain.PageDTO;
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
	public void getList(Model model, Criteria cri) {
		log.info("list request");
		
		List<BoardDTO> list=service.getList(cri); // 사용자가 요청한 번호에 맞는 게시물 목록 요청
		int total=service.getTotalCnt(); // 전체 게시물 개수
		
		model.addAttribute("list", list); // 목록 정보 넘기기
		model.addAttribute("pageDTO", new PageDTO(cri, total)); // 페이지 관련 정보 넘기기
	}
	
	// register.jsp 보여주기
	@GetMapping("/register")
	public void registerForm() {
		log.info("register form request");
	}
	
	@PostMapping("/register")
	public String register(BoardDTO boardDTO, RedirectAttributes rttr) {
		log.info("register request "+boardDTO);
		
		boolean insertFlag=service.register(boardDTO);
		if(insertFlag) {
			log.info("글 번호: "+boardDTO.getBno()); // 게시글을 작성할 때마다 로그에 글 번호가 출력된다.
			rttr.addFlashAttribute("result", boardDTO.getBno()); // addFlashAttribute: 주소줄에 넘기지 않고 session에 잠시 담아두는 방식
			return "redirect:/board/list";
		} else {
			return "/board/register";
		}
	}
	
	// http://localhost:8080/board/read?bno=21
	// http://localhost:8080/board/modify?bno=21
	@GetMapping({"/read", "/modify"})
	public void readGet(int bno, Model model) {
		log.info("read request "+bno);
		
		// bno에 해당하는 내용 가져오기
		BoardDTO dto=service.read(bno);
		model.addAttribute("dto", dto);
	}
	
	@PostMapping("/modify")
	public String modifyPost(BoardDTO boardDTO, RedirectAttributes rttr) {
		// 수정 성공 시 리스트로 가기
		service.modify(boardDTO);
		rttr.addFlashAttribute("result", "success");
		return "redirect:/board/list";
	}
	
	@GetMapping("/remove")
	public String removeGet(int bno, RedirectAttributes rttr) {
		// 삭제 성공 시 리스트로 가기
		service.remove(bno);
		rttr.addFlashAttribute("result", "success");
		return "redirect:/board/list";
	}
	
}
