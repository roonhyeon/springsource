package com.spring.console;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.service.SampleService;

public class SampleMain {

	public static void main(String[] args) {
		
		ApplicationContext ctx=new ClassPathXmlApplicationContext("config.xml");
		
		SampleService service=(SampleService)ctx.getBean("sample");
		
		String data="Starry\r\n";
		data+="Starry night\r\n";
		data+="Paint your paletter blue and grey\r\n";
		data+="Look out on a summer'day";
		
		service.addData(data);

	}

}
