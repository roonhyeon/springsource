package com.spring.console;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.domain.MemberDTO;
import com.spring.service.MemberService;

public class MemberMain {

	public static void main(String[] args) {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("config.xml");
		MemberService service=(MemberService)ctx.getBean("member");
		
//		// 추가
//		MemberDTO dto=new MemberDTO();
//		dto.setUserid("kang123");
//		dto.setPassword("kang123");
//		dto.setName("강호동");
//		dto.setGender("남");
//		dto.setEmail("kang123@naver.com");
//		
//		boolean result1=service.insertMember(dto);
//
//		System.out.println(result1 ? "입력 성공":"입력 실패");
		
//		// 수정
//		MemberDTO updateDto=new MemberDTO();
//		updateDto.setUserid("kong123");
//		updateDto.setPassword("kong123");
//		updateDto.setEmail("ko123@naver.com");
//		System.out.println(service.updateMember(updateDto) ? "수정 성공":"수정 실패");
		
		// 삭제
//		System.out.println(service.deleteMember("kong123","kong123") ? "삭제 성공":"삭제 실패");
		
		// 특정 멤버 조회
//		System.out.println(service.getRow("kang123", "kang123"));
		
		// 전체 멤버 조회
		System.out.println(service.getRows());

	}

}
