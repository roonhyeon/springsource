package com.spring.console;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.domain.BookDTO;
import com.spring.service.BookService;
import com.spring.service.BookServiceImpl;

public class BookMain { // 화면에 출력해주기용 클래스

	public static void main(String[] args) {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("config.xml"); // 컨테이너를 띄우기 위한 코드
		
		// Service 호출
		BookService service=(BookService)ctx.getBean("bookService"); // bookService에 해당하는 거 찾아와 봐
		
		// 도서 추가
//		BookDTO insertDto=new BookDTO(1006, "스프링", "쿨러", 15000, "스프링 프레임워크 의존성");
//		if(service.insertBook(insertDto)) {
//			System.out.println("입력 성공");
//		}
		
		// 도서 수정
//		BookDTO updateDto=new BookDTO();
//		updateDto.setCode(1006);
//		updateDto.setPrice(25000);
		if(service.updateBook(30000,1006)) {
			System.out.println("수정 성공");
		} // Exception in thread "main" org.mybatis.spring.MyBatisSystemException: 
		  // nested exception is org.apache.ibatis.binding.BindingException: Parameter 'price' not found. 
		  // Available parameters are [arg1, arg0, param1, param2]
		  // 해결방법: @Param을 사용해준다.
//		
//		// 특정 도서 조회
//		BookDTO row=service.getBook(1006);
//		System.out.println(row);
//		
//		// 도서 삭제
//		if(service.deleteBook(1012)) {
//			System.out.println("삭제 성공");
//		}
		
		// 전체 도서 목록 가져오기
		//BookService service=new BookServiceImpl(new BookDAO());
//		List<BookDTO> list=service.getBookList();
//		
//		for (BookDTO bookDTO : list) {
//			System.out.println(bookDTO);
//		}
	}

}
