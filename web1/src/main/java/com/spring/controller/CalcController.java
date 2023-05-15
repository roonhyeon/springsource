package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.domain.AddDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CalcController {
	
	@GetMapping("/add")
	public void addForm() {
		log.info("add request");
	}
	
	// 사용자 입력값 가져오기
	// 1) HttpServletRequest 사용하기
	// 2) 변수 사용: 변수명은 폼 태그 name과 일치해야 한다. 만약 일치하지 않는다면 @RequestParam을 사용한다.
	// 3) 바인딩 객체 사용
//	@PostMapping("/add")
//	public void addPost(int num1, int num2) { // integer.parseint 안해주고 바로 이렇게 쓰면 된다.
//		log.info("add post request");
//		log.info("num1 = "+num1);
//		log.info("num2 = "+num2);
//		log.info("result = "+(num1+num2));
//	}
	
//	@PostMapping("/add")
//	public void addPost(AddDTO dto) {
//		log.info("add post request");
//		log.info("num1 = "+dto.getNum1());
//		log.info("num2 = "+dto.getNum2());
//		log.info("result = "+(dto.getNum1()+dto.getNum2()));
//	}
	
	@PostMapping("/add")
	public String addPost(@ModelAttribute("add") AddDTO dto, @ModelAttribute("page") String page, Model model) {
		log.info("add post request");
		log.info("num1 = "+dto.getNum1());
		log.info("num2 = "+dto.getNum2());
		log.info("page = "+page);
		int result=dto.getNum1()+dto.getNum2();
		log.info("result = "+result); // result 값을 result.jsp에서 사용하고 싶다면? => Model 객체를 사용한다.==request.setAttribute()
		
		model.addAttribute("result", result);
//		model.addAttribute("page", page); // page 값을 유지하고 jsp로 가서 쓰고 싶을 때
		
		return "result";
	}

}
