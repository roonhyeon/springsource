package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.domain.MemberDTO;
import com.spring.mapper.MemberMapper;

@Service("member")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper mapper;

	@Override
	public boolean insertMember(MemberDTO dto) {
		return mapper.insert(dto)==1 ? true:false;
	}

	@Override
	public boolean updateMember(MemberDTO dto) {
		return mapper.update(dto)==1 ? true:false;
	}

	@Override
	public boolean deleteMember(String userid, String password) {
		return mapper.delete(userid, password)==1 ? true:false;
	}

	@Override
	public MemberDTO getRow(String userid, String password) {
		return mapper.getRow(userid, password);
	}

	@Override
	public List<MemberDTO> getRows() {
		return mapper.getRows();
	}

}
