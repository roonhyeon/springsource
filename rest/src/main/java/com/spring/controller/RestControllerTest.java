package com.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring.domain.SampleDTO;

@RestController
public class RestControllerTest {
	
	// 일반 컨트롤러에서 return 값은 view 이름
	// Rest 컨트롤러에서 return 값은 데이터
	
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello World"; // 단순히 문자열 Hello World라는 의미 => 브라우저 창에 Hello World 출력된다.
	}
	
	// produces: 리턴 값을 json 형태로 보낼거야
	// 자바 객체를 json 형태로 변경해주는 라이브러리가 필요하다.
	@GetMapping(value="/send", produces=MediaType.APPLICATION_JSON_VALUE)
	public SampleDTO sendDto() {
		return new SampleDTO("1234", "홍", "길동");
	}
	
	@GetMapping(value="/sends", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<SampleDTO> sendList() {
		
		List<SampleDTO> list=new ArrayList<SampleDTO>();
		list.add(new SampleDTO("1234", "홍", "길동"));
		list.add(new SampleDTO("1235", "고", "길동"));
		list.add(new SampleDTO("1236", "최", "길동"));
		list.add(new SampleDTO("1237", "김", "길동"));
		
		return list;
	}
	
	// ResponseEntity: 상태코드(200, 400, 500)와 데이터를 같이 전송
	@GetMapping(value="/check", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SampleDTO> check(double height, double weight) {
		
		SampleDTO dto=new SampleDTO("1234", ""+height, ""+weight);
		
		ResponseEntity<SampleDTO> entity=null;
		
		if(height<160) {
			entity=new ResponseEntity<SampleDTO>(dto, HttpStatus.BAD_REQUEST);
		}else {
			entity=new ResponseEntity<SampleDTO>(dto, HttpStatus.OK);
		}
		
		return entity;
	}

	// 경로에 있는 특정 값을 사용하고 싶다면?
	// http://localhost:8080/product/bags/1001 => bags(카테고리명), 1001(상품 아이디)
	// http://localhost:8080/product/shirt/1003 => shirt(카테고리명), 1003(상품 아이디)
	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath(@PathVariable("cat") String cat, @PathVariable("pid") String pid) {
		return new String[] {
				"category : "+cat,
				"productId : "+pid
		};
	}
}
