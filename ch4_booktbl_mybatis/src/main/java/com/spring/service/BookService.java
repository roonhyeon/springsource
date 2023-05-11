package com.spring.service;

import java.util.List;

import com.spring.domain.BookDTO;

public interface BookService {
	
	boolean insertBook(BookDTO insertDto);
	boolean updateBook(int price, int code);
	boolean deleteBook(int code);
	BookDTO getBook(int code);
	List<BookDTO> getBookList();

}
