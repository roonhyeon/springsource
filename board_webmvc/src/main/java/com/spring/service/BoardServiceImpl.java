package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.domain.BoardDTO;
import com.spring.domain.Criteria;
import com.spring.mapper.AttachMapper;
import com.spring.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper mapper;

	@Autowired
	private AttachMapper attachMapper;
	
	@Override
	public List<BoardDTO> getList(Criteria cri) {
		return mapper.list(cri);
	}

	@Transactional
	@Override
	public boolean register(BoardDTO boardDTO) {
		// board 테이블 + attach 테이블 등록
		boolean insertFlag=mapper.insert(boardDTO)==1 ? true:false;
		
		// 첨부파일 여부 확인
		if(boardDTO.getAttachList()==null || boardDTO.getAttachList().size()==0) {
			return insertFlag; // 첨부파일이 없으면 걍 board table 내용만 보내면 된다.
		}
		
		// return insertFlag가 안됐을 때(첨부파일이 존재하면) 여기로 넘어온다.
		boardDTO.getAttachList().forEach(attach -> {
			attach.setBno(boardDTO.getBno()); // board의 bno를 attachList의 bno에 대입해주는 과정 
			attachMapper.insert(attach);
		});
		return insertFlag;
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
	public int getTotalCnt(Criteria cri) {
		return mapper.totalCnt(cri);
	}

}
