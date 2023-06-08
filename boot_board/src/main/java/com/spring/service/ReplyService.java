package com.spring.service;

import java.util.List;

import com.spring.domain.Criteria;
import com.spring.domain.ReplyDTO;
import com.spring.domain.ReplyPageDTO;

public interface ReplyService {
	public ReplyDTO read(int rno);
	public boolean insert(ReplyDTO replyDTO);
	// 댓글 목록만 처리
//	public List<ReplyDTO> readAll(Criteria cri, int bno);
	
	// 댓글 총 수, 댓글 목록 처리
	public ReplyPageDTO readAll(Criteria cri, int bno);
	public boolean update(ReplyDTO dto);
	public boolean delete(int rno);

}
