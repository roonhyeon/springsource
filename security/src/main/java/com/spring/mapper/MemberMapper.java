package com.spring.mapper;

import com.spring.domain.SpUserAuthorityDTO;
import com.spring.domain.SpUserDTO;

public interface MemberMapper {
	public int register(SpUserDTO dto);
	public int registerAuth(SpUserAuthorityDTO dto);
	
	public SpUserDTO read(String userid);

}
