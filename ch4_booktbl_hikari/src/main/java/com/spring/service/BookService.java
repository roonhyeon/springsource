package com.spring.service;

import java.util.List;

import com.spring.domain.BookDTO;

public interface BookService {
	
	boolean insertBook(BookDTO insertDto);
	boolean updateBook(BookDTO updateDto);
	boolean deleteBook(int code);
	BookDTO getBook(int code);
	List<BookDTO> getBookList();

}
