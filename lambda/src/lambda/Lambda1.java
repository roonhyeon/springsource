package lambda;

/* 
 * 람다식(자바 8부터 추가됨)
 * -함수형 언어
 * -메서드를 하나의 '식'으로 표현
 * -익명 클래스 객체
 * 
 * -함수형 인터페이스 다양하게 제공(java.util.Function 패키지)
 */

public class Lambda1 {
	int max(int a, int b) {
		return a > b ? a : b;
	}	
	// 람다식
	// (int a, int b)->{return a > b ? a : b;}
	// (int a, int b) -> a > b ? a : b // 실행할 구문이 하나라면 중괄호 생략 가능
	// (a, b) -> a > b ? a : b;

	
	void printVar(String name, int i) {
		System.out.println(name + " = " + i);
	}
	// 람다식
//	(String name, int i) -> {System.out.println(name + " = " + i);}
//	(String name, int i) -> System.out.println(name + " = " + i)
//	(name, i) -> System.out.println(name + " = " + i);
	
	
	int square(int x) {
		return x * x;
	}
	// 람다식
//	(int x) -> {return x * x;}
//	(int x) -> x * x
//	(x) -> x * x
	
	
	int roll() {
		return (int)(Math.random()*6);
	}
	// 람다식
//	() -> {return (int)(Math.random()*6);}
//	() -> (int)(Math.random()*6)
}
