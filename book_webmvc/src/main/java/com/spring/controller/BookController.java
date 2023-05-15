package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.domain.BookDTO;
import com.spring.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookService service;
	
//	// /product/insert.jsp 보여주기 => http://localhost:8080/book/insert + GET
//	@GetMapping("/insert")
//	public String insertForm() {
//		log.info("insert form request");
//		return "/product/insert";
//	}
//	
//	// http://localhost:8080/book/insert + POST
//	@PostMapping("/insert")
//	public String insertPost(BookDTO dto) {
//		log.info("insert request");
//		
//		// 사용자 입력값 가져오기: HttpServletRequest, 변수, ~~DTO => 3가지 방법 중 하나 선택
//		// 서비스 작업
//		// exception 방지를 위해 try~catch
//		try {
//			boolean insertFlag=service.insert(dto);
//			if(insertFlag) {
//				// 입력 성공시 목록 보여주기
//				return "redirect:/book/list"; // jsp를 띄워주는 것이 아니라 경로를 찾으러 가야한다.
//			} else {
//				// 입력 실패시 insert.jsp 보여주기
//				return "/product/insert";
//			}	
//		} catch (Exception e) {
//			System.out.println("오류");
//			return "/product/insert";
//		}
//	}
//	
//	// http://localhost:8080/book/list + GET => list.jsp 보여주기
//	@GetMapping("/list")
//	public String listForm(Model model) {
//		log.info("list form request");
//		
//		// 사용자가 작성한 값 가져오기
//		// service 작업
//		List<BookDTO> list=service.getList();
//		
//		// list에 담긴 정보를 list.jsp에 보여주고 싶다면?
//		model.addAttribute("list", list); // ==request.setAttribute()
//		
//		return "/product/list";
//	}
//	
//	// http://localhost:8080/book/read?code=1001 + GET
//	@GetMapping("/read")
//	public String readGet(int code, Model model) {
//		log.info("read request"+code);
//		
//		BookDTO dto=service.get(code);	
//		model.addAttribute("dto", dto);
//		
//		return "/product/read";
//	}
	
	// ## jsp 파일들을 book 폴더 안에 넣어주면 void가 가능해진다.
	// /book/insert.jsp 보여주기 => http://localhost:8080/book/insert + GET
		@GetMapping("/insert")
		public void insertForm() {
			log.info("insert form request");
		}
		
		// http://localhost:8080/book/insert + POST
		@PostMapping("/insert")
		public String insertPost(BookDTO dto) {
			log.info("insert request");
			
			// 사용자 입력값 가져오기: HttpServletRequest, 변수, ~~DTO => 3가지 방법 중 하나 선택
			// 서비스 작업
			// exception 방지를 위해 try~catch
			try {
				boolean insertFlag=service.insert(dto);
				if(insertFlag) {
					// 입력 성공시 목록 보여주기
					return "redirect:/book/list"; // jsp를 띄워주는 것이 아니라 경로를 찾으러 가야한다.
				} else {
					// 입력 실패시 insert.jsp 보여주기
					return "/book/insert";
				}	
			} catch (Exception e) {
				System.out.println("오류");
				return "/book/insert";
			}
		}
		
		// http://localhost:8080/book/list + GET => /book/list.jsp 보여주기
		@GetMapping("/list")
		public void listForm(Model model) {
			log.info("list form request");
			
			// 사용자가 작성한 값 가져오기
			// service 작업
			List<BookDTO> list=service.getList();
			
			// list에 담긴 정보를 list.jsp에 보여주고 싶다면?
			model.addAttribute("list", list); // ==request.setAttribute()
		}
		
		// http://localhost:8080/book/read?code=1001 + GET => /book/read.jsp
		@GetMapping("/read")
		public void readGet(int code, Model model) {
			log.info("read request"+code);
			
			BookDTO dto=service.get(code);	
			model.addAttribute("dto", dto);
		}

}
