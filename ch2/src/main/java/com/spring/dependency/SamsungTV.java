package com.spring.dependency;

public class SamsungTV implements TV {
	
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
