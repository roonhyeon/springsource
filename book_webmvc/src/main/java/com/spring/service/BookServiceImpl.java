package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.domain.BookDTO;
import com.spring.mapper.BookMapper;

@Service
public class BookServiceImpl implements BookService{ // DB 메서드 호출하는 클래스
	
	// DAO 메서드(== Mapper Interface 호출)
	@Autowired
	private BookMapper mapper;

	@Override
	public boolean insert(BookDTO dto) {
		return mapper.insert(dto)==1 ? true:false;
	}

	@Override
	public boolean delete(int code) {
		return mapper.delete(code)==1 ? true:false;
	}

	@Override
	public boolean update(BookDTO dto) {
		return mapper.update(dto)==1 ? true:false;
	}

	@Override
	public BookDTO get(int code) {
		return mapper.read(code);
	}

	@Override
	public List<BookDTO> getList() {
		return mapper.getRows();
	}

	@Override
	public List<BookDTO> getSearchList(String criteria, String keyword) {
		return mapper.search(criteria, keyword);
	}

}
