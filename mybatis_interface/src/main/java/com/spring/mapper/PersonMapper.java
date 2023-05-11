package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;

import com.spring.domain.PersonDTO;

public interface PersonMapper {
	
	// 1. interface+annotaion 방식
//	@Insert("insert into person(id, name) values(#{id},#{name})")
//	public int insert(PersonDTO insert);
	
	// 2. interface+XML 방식
	// 각각의 메서드명과 PersonMapper.XML에 작성한 id명이 일치해야 바로 찾을 수 있다.
	public int insertPerson(PersonDTO insert);
	public int updatePerson(PersonDTO insert);
	public int deletePerson(String id);
	public PersonDTO selectOne(String id);
	public List<PersonDTO> selectAll();

}
