package com.spring.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.domain.PersonDTO;

@Repository
public class PersonDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace="com.spring.mapper.PersonMapper";
	
	public int insert(PersonDTO insert) {
		return sqlSession.insert(namespace+".insertPerson",insert); // PersonMapper에 있는 insertPerson 메서드를 실행해줘
	}
	
	public int update(PersonDTO update) {
		return sqlSession.update(namespace+".updatePerson",update); // PersonMapper에 있는 insertPerson 메서드를 실행해줘
	}
	
	public int delete(String id) {
		return sqlSession.delete(namespace+".deletePerson",id); // PersonMapper에 있는 insertPerson 메서드를 실행해줘
	}
	
	public PersonDTO getRow(String id) {
		return sqlSession.selectOne(namespace+".selectOne",id);
	}
	
	public List<PersonDTO> getRows() {
		return sqlSession.selectList(namespace+".selectAll");
	}


}
