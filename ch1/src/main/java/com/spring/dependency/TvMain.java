package com.spring.dependency;

public class TvMain {
    String str="String"; // 객체를 멤버변수로 설정시 기본값은 null, 이 객체를 사용하고 싶으면 인스턴스 new 생성(생성 안하면 NullPointerException이 뜬다.)하거나 값 부여.
	public static void main(String[] args) {
		
		// 생성자를 이용한 멤버 변수 초기화
//		SonySpeaker speaker=new SonySpeaker();
//		TV tv=new SamsungTV(speaker);
		
//		TV tv=new SamsungTV(new SonySpeaker());
		
		// setter를 사용한 멤버 변수 초기화
		SamsungTV tv=new SamsungTV();
		tv.setSpeaker(new SonySpeaker());
		
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		
//		TvMain obj=new TvMain();
//		obj.test();
//	}
//	
//	public void test() {
//		System.out.println(str);
//		System.out.println(str.length());
	}

}
