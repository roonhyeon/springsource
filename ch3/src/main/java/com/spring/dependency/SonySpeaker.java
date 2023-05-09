package com.spring.dependency;

import org.springframework.stereotype.Component;

@Component("sony")
public class SonySpeaker implements Speaker {
	
	public SonySpeaker() {
		System.out.println("SonySpeaker 인스턴스 생성");
	}

	@Override
	public void volumeUp() {
		System.out.println("SonySpeaker Volume Up");
	}

	@Override
	public void volumeDown() {
		System.out.println("SonySpeaker Volume Down");
	}

}
