package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller // @Component 자식
@RequestMapping("/sample") // option => 'BasicController는 http://localhost:8080/sample/** 요청에 응답하는 컨트롤러야'라는 의미
public class BasicController {
	// controller 안에 일반 메서드 작성 가능
    // 일반 메서드는 @RequestMapping annotation을 붙이지 않으면 된다.

//	@RequestMapping("/basic") // http://localhost:8080/sample/basic + get 방식으로 출력
	@GetMapping("/basic") // 메서드에만 사용 가능
	public void basic() {
		log.info("basic......요청");
		
		// 컨트롤러 실행 후(없을 수도 있음) ViewResolver가 실행된다. 
		// 리턴이 있다면 /WEB-INF/views/리턴값.jsp
		
		// 리턴이 없다면 주소줄 문자열 /WEB-INF/views/sample/basic.jsp으로 만들어서 결과 리턴
		// [/WEB-INF/views/sample/basic.jsp]
	}
}
