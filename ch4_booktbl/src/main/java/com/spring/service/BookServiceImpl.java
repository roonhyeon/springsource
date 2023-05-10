package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.domain.BookDTO;
import com.spring.persistence.BookDAO;

@Service("bookService") // main에서 필요한 new BookServiceImpl 역할
public class BookServiceImpl implements BookService {
	
	@Autowired // DAO의 new BookDAO() 된 것을 가져오기
	private BookDAO bookDAO; // 2. 이렇게 객체 생성해서 항상 가지고 있어야 한다.
	
//	public BookServiceImpl(BookDAO bookDAO) {
//		this.bookDAO=bookDAO;
//	}

	@Override
	public boolean insertBook(BookDTO insertDto) {
		return bookDAO.insert(insertDto);
	}

	@Override
	public boolean updateBook(BookDTO updateDto) {
		return bookDAO.update(updateDto);
	}

	@Override
	public boolean deleteBook(int code) {
		return bookDAO.delete(code);
	}

	@Override
	public BookDTO getBook(int code) {
		return bookDAO.getRow(code);
	}

	@Override
	public List<BookDTO> getBookList() {
		return bookDAO.getRows(); // 1. 여기서 bookDAO를 불러야하므로
	}

}
