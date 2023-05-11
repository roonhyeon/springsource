package com.spring.console;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.domain.PersonDTO;
import com.spring.service.PersonService;

public class PersonMain {

	public static void main(String[] args) {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("config.xml");
		
		PersonService service=(PersonService)ctx.getBean("person");
		
		// 삽입
//		PersonDTO insertPerson=new PersonDTO("person1","강호동");
//		System.out.println(service.insertPerson(insertPerson)?"성공":"실패");
		
//		List<PersonDTO> list=service.getRows();
//		for (PersonDTO personDTO : list) {
//			System.out.println(personDTO);
//		}
		
//		// 수정
//		System.out.println(service.updatePerson(new PersonDTO("person1","강동주"))?"성공":"실패");
//		
//		// 삭제
//		System.out.println(service.deletePerson("person1")?"성공":"실패");
//		
//		// 특정인 조회
		System.out.println(service.getRow("hong123"));

	}

}
