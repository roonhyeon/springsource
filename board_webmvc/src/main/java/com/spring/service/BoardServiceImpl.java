package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.domain.BoardDTO;
import com.spring.domain.Criteria;
import com.spring.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper mapper;

	@Override
	public List<BoardDTO> getList(Criteria cri) {
		return mapper.list(cri);
	}

	@Override
	public boolean register(BoardDTO boardDTO) {
		return mapper.insert(boardDTO)==1 ? true:false;
	}

	@Override
	public BoardDTO read(int bno) {
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardDTO boardDTO) {
		return mapper.modify(boardDTO)==1 ? true:false;
	}

	@Override
	public boolean remove(int bno) {
		return mapper.remove(bno)==1 ? true:false;
	}

	@Override
	public int getTotalCnt() {
		return mapper.totalCnt();
	}

}
