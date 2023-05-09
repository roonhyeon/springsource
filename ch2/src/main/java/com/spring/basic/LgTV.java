package com.spring.basic;

public class LgTV implements TV {
	@Override
	public void powerOn() {
		System.out.println("LGTV - 전원 On");
	}
	@Override
	public void powerOff() {
		System.out.println("LGTV - 전원 Off");
	}
	@Override
	public void volumeUp() {
		System.out.println("LGTV - 볼륨 Up");
	}
	@Override
	public void volumeDown() {
		System.out.println("LGTV - 볼륨 Down");
	}

}
