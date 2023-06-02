package lambda;

// 람다식은 어디에 포함해서 쓸 것인가??
@FunctionalInterface // 필수로 지정해야 하는 요소는 아니지만, 하나의 추상 메서드만 정의 되도록 컴파일 단계에서 처리한다.
public interface Lambda4 {
	public int max(int x, int y);
//	public void method2(); // 메서드는 하나만 존재할 수 있다.

}


