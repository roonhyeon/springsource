package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.domain.Criteria;
import com.spring.domain.ReplyDTO;
import com.spring.domain.ReplyPageDTO;
import com.spring.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper reMapper;
	
	@Override
	public ReplyDTO read(int rno) {
		return reMapper.read(rno);
	}

	@Override
	public boolean insert(ReplyDTO replyDTO) {
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

	@Override
	public boolean delete(int rno) {
		return reMapper.remove(rno)==1 ? true:false;
	}

}
