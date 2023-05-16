package com.spring.service;

import java.util.List;

import com.spring.domain.BookDTO;

public interface BookService {
	public boolean insert(BookDTO dto);
	public boolean delete(int code);
	public boolean update(BookDTO dto);
	public BookDTO get(int code);
	public List<BookDTO> getList();
	public List<BookDTO> getSearchList(String criteria, String keyword);

}
