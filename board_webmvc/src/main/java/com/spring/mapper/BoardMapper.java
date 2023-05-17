package com.spring.mapper;

import java.util.List;

import com.spring.domain.BoardDTO;

public interface BoardMapper {
	public List<BoardDTO> list();
	public int insert(BoardDTO boardDTO);

}
