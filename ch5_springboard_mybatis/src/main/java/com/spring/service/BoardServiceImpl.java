package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.spring.domain.BoardDTO;
import com.spring.mapper.BoardMapper;

@Service("board")
public class BoardServiceImpl implements BoardService {
	
	@Autowired // DAO의 @Repository를 통해 new 해서 형성된 것을 가져옴 
	private BoardMapper mapper;

	@Override
	public boolean insertBoard(BoardDTO dto) {
		return mapper.insert(dto)==1 ? true: false;
	}

	@Override
	public boolean updateBoard(BoardDTO dto) {
		return mapper.update(dto)==1 ? true: false;
	}

	@Override
	public boolean deleteBoard(int bno) {
		return mapper.delete(bno)==1 ? true: false;
	}

	@Override
	public BoardDTO getRow(int bno) {
		return mapper.getRow(bno);
	}

	@Override
	public List<BoardDTO> getRows() {
		return mapper.getRows();
	}

}
