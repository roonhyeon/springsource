package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.domain.BookDTO;

public interface BookMapper {
	public List<BookDTO> getRows();
	public int insert(BookDTO dto);
	public BookDTO read(int code);
	public int update(BookDTO dto);
	public int delete(int code);
	public List<BookDTO> search(@Param("criteria") String criteria, @Param("keyword") String keyword);

}
