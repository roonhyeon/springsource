package com.spring.dependency;

import org.springframework.stereotype.Component;

@Component("apple") // 빈(인스턴스) 생성 == AppleSpeaker 객체 생성해줘
public class AppleSpeaker implements Speaker {
	
	public AppleSpeaker() {
		System.out.println("AppleSpeaker 인스턴스 생성");
	}

	@Override
	public void volumeUp() {
		System.out.println("AppleSpeaker Volume Up");
	}

	@Override
	public void volumeDown() {
		System.out.println("AppleSpeaker Volume Down");
	}

}
