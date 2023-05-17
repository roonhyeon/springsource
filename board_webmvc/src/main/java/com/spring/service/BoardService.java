package com.spring.service;

import java.util.List;

import com.spring.domain.BoardDTO;

public interface BoardService {
	// 전체 리스트 가져오기
	public List<BoardDTO> getList();
	// 글 등록
	public boolean register(BoardDTO boardDTO);

}
