package com.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.domain.CustomUser;
import com.spring.domain.MemberDTO;
import com.spring.mapper.MemberMapper;

@Service("detail")
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private MemberMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		MemberDTO memberDTO=mapper.read(username);
		
		// memberDTO는 User 클래스를 상속 받아서 만든 클래스를 이용해서 리턴해야 한다.
		
		return new CustomUser(memberDTO);
	}

}