package lambda;

public class LambdaEx2 {
	public static void main(String[] args) {
		// Lambda2 인터페이스를 사용하고 싶다면??
		// 1) 구현 클래스 작성: 클래스명 implements Lambda2
		
		// 2) 딱 한번만 쓰고 안 쓸 것 같은 구현 클래스는 익명 구현 클래스로 작성
//		Lambda2 lambda=new Lambda2() {
//			@Override
//			public void method() {
//				System.out.println("익명 구현 클래스");
//			}
//		};
//		
//		lambda.method();
		
		// 위 식을 람다식으로 심플하게
		Lambda2 lambda=() -> System.out.println("익명 구현 클래스");
		lambda.method();
		
		
		lambda=()->{
			int i=10;
			System.out.println(i*i);
		};
		lambda.method();
	}

}
