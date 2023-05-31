package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.domain.AttachFileDTO;
import com.spring.domain.BoardDTO;
import com.spring.domain.Criteria;
import com.spring.mapper.AttachMapper;
import com.spring.mapper.BoardMapper;
import com.spring.mapper.ReplyMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper mapper;

	@Autowired
	private AttachMapper attachMapper;
	
	@Autowired
	private ReplyMapper replyMapper;
	
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
		
//		BoardDTO dto=mapper.readAttach(bno);
//		log.info("상세 + 파일첨부 "+dto);
		
		return mapper.read(bno);
	}

	@Transactional // 서로 다른 테이블에 접근하고 있으므로
	@Override
	public boolean modify(BoardDTO boardDTO) {
		boolean modifyFlag = mapper.modify(boardDTO)==1 ? true:false;
		
		// 기존 첨부목록 제거
		attachMapper.deleteAll(boardDTO.getBno());
		
		// 첨부파일이 있다면
		if(boardDTO.getAttachList()==null || boardDTO.getAttachList().size()==0) {
			return modifyFlag;
		}
		
		// 첨부목록 삽입
		boardDTO.getAttachList().forEach(attach -> {
			attach.setBno(boardDTO.getBno());
			attachMapper.insert(attach);
		});
		return modifyFlag;
	}

	@Transactional // 하나라도 실패 시에 되돌리기 위한 장치
	@Override
	public boolean remove(int bno) {
		
		// 자식 댓글 삭제
		replyMapper.deleteAll(bno);
		
		// 첨부파일 삭제
		attachMapper.deleteAll(bno);
		
		return mapper.remove(bno)==1 ? true:false;
	}

	@Override
	public int getTotalCnt(Criteria cri) {
		return mapper.totalCnt(cri);
	}

	@Override
	public List<AttachFileDTO> getAttachList(int bno) {
		return attachMapper.getRow(bno);
	}

}
