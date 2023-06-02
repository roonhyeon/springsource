package stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/*
 * 스트림
 * -컬렉션(List, Set, Map), 배열에 데이터를 담고 원하는 결과를 얻어내기 위해 for문과 Iterator를 사용한다.
 * => 데이터마다 다른 방식으로 다뤄야 한다, 재사용성이 떨어진다, 같은 기능의 메서드들이 중복으로 정의된다.
 * => List 정렬 시 Collections.sort() 사용, 배열 정렬 시 Arrays.sort() 사용
 * 
 * => 이러한 위의 두 문제점을 해결하기 위해 스트림이 추가되었다.
 * => 스트림을 통해 서로 다른 데이터라도 동일한 방식으로 처리할 수 있다.
 * 
 * 스트림 특징
 * 1) 데이터 소스를 변경하지 않는다.
 * 2) 일회용이다.
 * 3) 작업을 내부 반복으로 처리한다.(받아서 forEach로 찍어낸다.)
 */

public class ForEach1 {
	public static void main(String[] args) {
		// 1. 기존 방식
		// 문자열 배열
		String[] strArr= {"aaa","ddd","ccc"};
		// 문자열 리스트
		List<String> strList=Arrays.asList(strArr);
		// 정렬 + 출력
		Arrays.sort(strArr); // 원본 정렬
		for(String string : strArr) {
			System.out.print(string+" ");
		}
		
		System.out.println();
		System.out.println("----------------------");
		
		Collections.sort(strList); // 원본 정렬
		for (String string : strList) {
			System.out.print(string+" ");
		}
		
		
		// 2. 스트림 방식
		System.out.println("\n----------- stream");
		// 문자열 배열
		String[] strArr1= {"aaa","ddd","ccc"};
		// 문자열 리스트
		List<String> strList1=Arrays.asList(strArr1);
		// 스트림 방식을 쓰기 위해 스트림 생성
		Stream<String> stream1=Arrays.stream(strArr1); // 배열은 Arrays 사용
		Stream<String> stream2=strList1.stream();
		// 정렬 + 출력
		stream1.sorted().forEach(System.out::println);
		stream2.sorted().forEach(System.out::println);
		// 원본? 그대로 유지
		System.out.println("원본 데이터");
		System.out.println(Arrays.toString(strArr1));
		System.out.println(strList1);
	}

}
