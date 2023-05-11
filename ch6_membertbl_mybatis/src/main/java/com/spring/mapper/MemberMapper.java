package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.domain.MemberDTO;

public interface MemberMapper {
	public int insert(MemberDTO dto);
	public int update(MemberDTO dto);
	public int delete(@Param("userid")String userid, @Param("password")String password);
	public MemberDTO getRow(@Param("userid")String userid, @Param("password")String password);
	public List<MemberDTO> getRows();

}
