package com.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.domain.AuthDTO;
import com.spring.domain.ChangeDTO;
import com.spring.domain.LoginDTO;
import com.spring.domain.MemberDTO;
import com.spring.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberMapper mapper;
	
	@Autowired
	private BCryptPasswordEncoder bPasswordEncoder;
	
	@Override
	public AuthDTO login(LoginDTO loginDTO) {
		// matches(암호화하기 전 코드, 암호화된 코드) => matches(1234, db 암호화된 문자)
		
		// 비밀번호가 일치하는가??
		// db에서 암호화된 비번 가져오기
		String encodePwd=mapper.getPass(loginDTO.getUserid());
	  
	    if (bPasswordEncoder.matches(loginDTO.getPassword(), encodePwd)) {
	    	return mapper.login(loginDTO.getUserid());
	    } else {
	    	return null;
	    }
	}

	@Override
	public boolean insert(MemberDTO memberDTO) {
		// 비밀번호 암호화하는 코드(encode는 원본 코드를 암호화하는 메서드임, matches는 암호화하기 전의 원본 코드와 암호화된 코드를 비교하여 비밀번호 일치 여부를 판단하는 메서드임)
		memberDTO.setPassword(bPasswordEncoder.encode(memberDTO.getPassword()));
		return mapper.insert(memberDTO)==1 ? true:false;
	}

	@Override
	public boolean dupId(String userid) {
		return mapper.dupId(userid)==0 ? true:false;
	}

	@Override
	public boolean remove(LoginDTO loginDTO) {
		// 암호화된 비밀번호 확인
		String encodePwd=mapper.getPass(loginDTO.getUserid());
		  
	    if (bPasswordEncoder.matches(loginDTO.getPassword(), encodePwd)) {
	    	return mapper.leave(loginDTO.getUserid())==1 ? true:false;
	    }
	    return false;
	}

	@Override
	public boolean update(ChangeDTO changeDTO) {
		// 현재 비밀번호 확인
		String encodePwd=mapper.getPass(changeDTO.getUserid());
		
		if (bPasswordEncoder.matches(changeDTO.getCurrentPwd(), encodePwd)) {
			// 변경 비밀번호 암호화
			changeDTO.setNewPwd(bPasswordEncoder.encode(changeDTO.getNewPwd()));
			
	    	return mapper.update(changeDTO)==1 ? true:false;
	    }
	    return false;
	}

	

}
