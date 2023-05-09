package com.spring.basic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// Spring framework
// 인스턴스를 생성하고 관리 => 모든 인스턴스를 생성하고 관리하는 것은 아니고 지정한 인스턴스("config.xml")만 처리, 인스턴스 생성 시 singleton 방식으로 생성하고 관리

public class HelloApp {

	public static void main(String[] args) {
		System.out.println("=== 컨테이너 구동 전 ===");
		
		// src/main/resources ==> ClassPath
		
		ApplicationContext ctx=new ClassPathXmlApplicationContext("config.xml");
		
		System.out.println("=== 컨테이너 구동 후 ===");
		MessageBean msg=(MessageBean)ctx.getBean("msg"); // config.xml의 id 찾아와 봐 
		msg.sayHello("홍길동");

	}

}
