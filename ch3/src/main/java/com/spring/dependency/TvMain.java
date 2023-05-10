package com.spring.dependency;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TvMain {

	public static void main(String[] args) {
		
		ApplicationContext ctx=new ClassPathXmlApplicationContext("config.xml");
		
//	    TV tv=(TV)ctx.getBean("samsungTV"); // @Component로 생성 시 id는 클래스명을 사용한다.(단, 앞글자는 소문자)
	    TV tv=(TV)ctx.getBean("tv"); // @Component에 값을 부여해주었다면 ex) @Component("tv") 그 값을 써주면 된다.
	    
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		
	}

}
