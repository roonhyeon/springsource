package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.domain.BookDTO;
import com.spring.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class BookController {
	
	@Autowired
	private BookService service;
	
	@GetMapping(value="/list", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BookDTO>> list(){
		log.info("리스트 요청 ");
		
		List<BookDTO> list=service.getList();
		return new ResponseEntity<List<BookDTO>>(list, HttpStatus.OK);
	}
	
	// http://localhost:8080/1001 + GET: code가 1001번인 도서 정보 조회
	// http://localhost:8080/1003 + GET: code가 1003번인 도서 정보 조회
	@GetMapping(value="/{code}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookDTO> get(@PathVariable("code") int code){
		log.info("특정 도서 요청 "+code);
		
		BookDTO row=service.get(code);
		return new ResponseEntity<BookDTO>(row, HttpStatus.OK);
	}
	
	// http://localhost:8080/1001 + DELETE: code가 1001번인 도서 삭제
	@DeleteMapping("/{code}")
	public ResponseEntity<String> remove(@PathVariable("code") int code){
		log.info("특정 도서 삭제 ");
		boolean result=service.delete(code);
		
		ResponseEntity<String> entity=null;
		String msg="";
		
		if(!result) {
			msg="fail";
			entity=new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
		}else {
			msg="success";
			entity=new ResponseEntity<String>(msg, HttpStatus.OK);
		}
		return entity;
	}
	
	// http://localhost:8080/1001 + PUT + 수정데이터
	// http://localhost:8080/update + PUT + 수정데이터
	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestBody BookDTO dto){
		log.info("도서정보수정 "+dto.getCode()+", "+dto.getPrice());
		
		return service.update(dto)? 
				new ResponseEntity<String>("success", HttpStatus.OK):
				new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/create")
	public ResponseEntity<String> create(@RequestBody BookDTO dto){
		log.info("도서정보추가 "+dto);
		
		return service.insert(dto)? 
				new ResponseEntity<String>("success", HttpStatus.OK):
				new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}

}
