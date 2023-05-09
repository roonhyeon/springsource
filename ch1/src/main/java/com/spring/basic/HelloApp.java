package com.spring.basic;

public class HelloApp {

	public static void main(String[] args) {
		MessageBean msg=new MessageBeanEn(); // 오른쪽에 실제 구현하는 객체 입력 => 오른쪽만 바꿔주면 된다.
		msg.sayHello("홍길동");
		
//		new MessageBeanEn().sayHello("홍길동");
		
		

	}

}
