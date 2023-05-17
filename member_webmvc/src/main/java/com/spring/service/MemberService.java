package com.spring.service;

import com.spring.domain.AuthDTO;
import com.spring.domain.ChangeDTO;
import com.spring.domain.LoginDTO;
import com.spring.domain.MemberDTO;

public interface MemberService {
	// 로그인
	public AuthDTO login(LoginDTO loginDTO);
	// 회원가입
	public boolean insert(MemberDTO memberDTO);
	// 중복 아이디 검사
	public boolean dupId(String userid);
	// 회원탈퇴
	public boolean remove(LoginDTO loginDTO);
	// 비밀번호 변경
	public boolean update(ChangeDTO changeDTO);

}
