package com.spring.dependency;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("tv") // SamsungTV 인스턴스(객체) 생성해줘
public class SamsungTV implements TV {
	//@Inject == @Autowired => 하는 일이 같다. 다만 어느 패키지의 것을 가져다 쓰는지의 차이다. inject는 javax가 제공하고 있고, autowired는 annotation이 제공하고 있다. 
	
	@Inject
	//@Autowired // 주입(스프링 컨테이너가 관리하는 빈 중에서 하나가 주입됨)
	@Qualifier("apple") // sonyspeaker 클래스에서 @component에 지정된 이름
	private Speaker speaker;  // 포함관계(has-a) with SonySpeaker
	
	// 매개 변수를 받지 않는 생성자: default 생성자
	public SamsungTV() {
		System.out.println("SamsungTV 인스턴스 생성-default 생성자");
	}	

	public SamsungTV(Speaker speaker) {
		super();
		this.speaker = speaker; // 멤버변수 초기화하는 코드
		System.out.println("SamsungTV 인스턴스 생성-인자 생성자");
	}
	
	public void setSpeaker(SonySpeaker speaker) {
		this.speaker = speaker;
	}
	
	@Override
	public void powerOn() {
		System.out.println("SamsungTV - 파워 On");
	}
	@Override
	public void powerOff() {
		System.out.println("SamsungTV - 파워 Off");
	}
	@Override
	public void volumeUp() {
//		System.out.println("SamsungTV - 볼륨 Up");
		speaker.volumeUp();
	}
	@Override
	public void volumeDown() {
//		System.out.println("SamsungTV - 볼륨 Down");
		speaker.volumeDown();
	}

}
