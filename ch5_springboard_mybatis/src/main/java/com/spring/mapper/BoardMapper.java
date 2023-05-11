package com.spring.mapper;

import java.util.List;

import com.spring.domain.BoardDTO;

public interface BoardMapper {
	public int insert(BoardDTO dto);
	public int delete(int bno);
	public int update(BoardDTO dto);
	public List<BoardDTO> getRows();
	public BoardDTO getRow(int bno);

}
