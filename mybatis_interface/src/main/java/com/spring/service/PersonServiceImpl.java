package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.domain.PersonDTO;
import com.spring.mapper.PersonMapper;

@Service("person")
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private PersonMapper mapper;

	@Override
	public boolean insertPerson(PersonDTO insert) {
		return mapper.insertPerson(insert)==1?true:false;
	}

	@Override
	public boolean updatePerson(PersonDTO update) {
		return mapper.updatePerson(update)==1?true:false;
	}

	@Override
	public boolean deletePerson(String id) {
		return mapper.deletePerson(id)==1?true:false;
	}

	@Override
	public List<PersonDTO> getRows() {
		return mapper.selectAll();
	}

	@Override
	public PersonDTO getRow(String id) {
		return mapper.selectOne(id);
	}

}
