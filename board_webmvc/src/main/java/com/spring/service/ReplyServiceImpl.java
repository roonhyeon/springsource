package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.domain.Criteria;
import com.spring.domain.ReplyDTO;
import com.spring.domain.ReplyPageDTO;
import com.spring.mapper.BoardMapper;
import com.spring.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper reMapper;
	
	@Autowired
	private BoardMapper mapper;
	
	@Override
	public ReplyDTO read(int rno) {
		return reMapper.read(rno);
	}

	@Transactional
	@Override
	public boolean insert(ReplyDTO replyDTO) {
		// 댓글 수 추가
		mapper.updateReplyCnt(replyDTO.getBno(), 1);
		// 댓글 등록
		return reMapper.insert(replyDTO)==1 ? true:false;
	}

	@Override
	public ReplyPageDTO readAll(Criteria cri, int bno) {
		List<ReplyDTO> list=reMapper.readAll(cri, bno);
		int replyCnt=reMapper.getCountByBno(bno);
		return new ReplyPageDTO(replyCnt, list);
	}

	@Override
	public boolean update(ReplyDTO dto) {
		return reMapper.modify(dto)==1 ? true:false;
	}

	@Transactional
	@Override
	public boolean delete(int rno) {
		ReplyDTO replyDTO=reMapper.read(rno);
		
		// 댓글 수 차감
		mapper.updateReplyCnt(replyDTO.getBno(), -1);
		// 댓글 제거
		return reMapper.remove(rno)==1 ? true:false;
	}

}
