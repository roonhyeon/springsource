package com.spring.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.spring.domain.MemberDTO;

@Mapper
public interface MemberMapper {
	public MemberDTO read(String userid);

}
