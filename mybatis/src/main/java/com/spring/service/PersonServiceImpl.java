package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.domain.PersonDTO;
import com.spring.persistence.PersonDAO;

@Service("person")
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private PersonDAO dao;

	@Override
	public boolean insertPerson(PersonDTO insert) {
		return dao.insert(insert)==1?true:false;
	}

	@Override
	public boolean updatePerson(PersonDTO update) {
		return dao.update(update)==1?true:false;
	}

	@Override
	public boolean deletePerson(String id) {
		return dao.delete(id)==1?true:false;
	}

	@Override
	public List<PersonDTO> getRows() {
		return dao.getRows();
	}

	@Override
	public PersonDTO getRow(String id) {
		return dao.getRow(id);
	}

}
