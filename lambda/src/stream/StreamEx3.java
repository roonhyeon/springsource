package stream;

import java.util.Arrays;
import java.util.List;

public class StreamEx3 {
	public static void main(String[] args) {
		
		List<String> list=Arrays.asList("사과","딸기","바나나","사과","배","수박");
		
		// 앞의 두 개의 요소는 건너뛰기, 출력은 3개만 하기
		// skip(), limit(): 스트림 자르기
		list.stream().skip(2).limit(3).forEach(System.out::println);
		
	}

}
