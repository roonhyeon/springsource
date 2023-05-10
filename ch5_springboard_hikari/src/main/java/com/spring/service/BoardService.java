package com.spring.service;

import java.util.List;

import com.spring.domain.BoardDTO;

public interface BoardService {
	boolean insertBoard(BoardDTO dto);
	boolean updateBoard(BoardDTO dto);
	boolean deleteBoard(int bno);
	BoardDTO getRow(int bno);
	List<BoardDTO> getRows();

}
