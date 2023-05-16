package com.spring.service;

import com.spring.domain.AuthDTO;
import com.spring.domain.LoginDTO;
import com.spring.domain.MemberDTO;

public interface MemberService {
	// 로그인
	public AuthDTO login(LoginDTO loginDTO);
	// 회원가입
	public boolean insert(MemberDTO memberDTO);

}
