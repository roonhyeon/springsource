package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.domain.BookDTO;

public interface BookMapper {
	public int insert(BookDTO dto);
	// 파라메터는 하나로 넘기는게 편하다.
	// 여러 개로 넘길 때는 @Param() annotation 사용해야 한다.
	public int update(@Param("price")int price, @Param("code")int code);
	public int delete(int code);
	public BookDTO getRow(int code);
	public List<BookDTO> getRows();

}
