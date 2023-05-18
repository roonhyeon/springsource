package com.spring.mapper;

import java.util.List;

import com.spring.domain.BoardDTO;
import com.spring.domain.Criteria;

public interface BoardMapper {
	public List<BoardDTO> list(Criteria cri);
	public int insert(BoardDTO boardDTO);
	public BoardDTO read(int bno);
	public int modify(BoardDTO boardDTO);
	public int remove(int bno);
	public int totalCnt();

}
