package com.spring.basic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TvMain {

	public static void main(String[] args) {
//		LgTV lgTV=new LgTV();
//		lgTV.turnOn();
//		lgTV.soundUp();
//		lgTV.soundDown();
//		lgTV.turnOff();
		
		ApplicationContext ctx=new ClassPathXmlApplicationContext("config.xml");
		
		TV samsungTV=(TV)ctx.getBean("samsung");
		samsungTV.powerOn();
		samsungTV.volumeUp();
		samsungTV.volumeDown();
		samsungTV.powerOff();
		
		TV samsungTV2=(TV)ctx.getBean("samsung");
		
		System.out.println(samsungTV==samsungTV2 ? "같음":"다름");

	}

}
