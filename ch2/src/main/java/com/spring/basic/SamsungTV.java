package com.spring.basic;

public class SamsungTV implements TV {
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
		System.out.println("SamsungTV - 볼륨 Up");
	}
	@Override
	public void volumeDown() {
		System.out.println("SamsungTV - 볼륨 Down");
	}

}
