package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.domain.BoardDTO;
import com.spring.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper mapper;

	@Override
	public List<BoardDTO> getList() {
		return mapper.list();
	}

	@Override
	public boolean register(BoardDTO boardDTO) {
		return mapper.insert(boardDTO)==1 ? true:false;
	}

}
