package com.spring.console;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.domain.BoardDTO;
import com.spring.service.BoardService;

public class BoardMain {

	public static void main(String[] args) {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("config.xml");
		
		BoardService service=(BoardService)ctx.getBean("board");
		
		// 게시글 작성
//		BoardDTO dto=new BoardDTO();
//		dto.setTitle("스프링");
//		dto.setContent("스프링 게시판");
//		dto.setWriter("홍길동");
//		
//		boolean result=service.insertBoard(dto);
//
//		System.out.println(result ? "입력 성공":"입력 실패");
		
		// 수정
		BoardDTO updateDto=new BoardDTO();
		updateDto.setBno(2);
		updateDto.setTitle("스프링 개념");
		updateDto.setContent("스프링 제어의 역전");
		boolean result=service.updateBoard(updateDto);
		System.out.println(result ? "수정 성공":"수정 실패");
		
		// 삭제
		System.out.println(service.deleteBoard(2) ? "삭제 성공":"삭제 실패");
		
		// 특정 게시물 조회
		System.out.println(service.getRow(1));
		
		// 전체 목록 조회
        List<BoardDTO> list=service.getRows();
		
		for (BoardDTO boardDTO : list) {
			System.out.println(boardDTO);
		}
		
	}

}
