package stream;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/*
 * 스트림 연산
 * 1. 다양한 연산을 이용해 복잡한 작업들을 간단히 처리한다.
 * 2. 연산의 종류
 *    1) 중간 연산: 연산 결과가 스트림. ex) map(), filter(), skip()....
 *    2) 최종 연산: 연산 결과가 스트림이 아닌 상태. ex) forEach(), collect(), reduce(), count(), max(), min()....
 */

public class StreamEx1 {
	public static void main(String[] args) {
		// 1. 파일 객체에서 경로는 제외하고 이름만 출력해보기
		List<File> list=new ArrayList<File>();
		
		list.add(new File("c:\\file1.txt"));
		list.add(new File("c:\\file2.txt"));
		list.add(new File("c:\\file3.txt"));
		list.add(new File("c:\\file4.txt"));
		list.add(new File("c:\\file5.txt"));

		// => 이름만 수집한 후 출력
		List<String> fileNames=new ArrayList<String>();
		for (File file : list) {
			fileNames.add(file.getName());
		}
		
		// => 최종 출력문
		for (String string : fileNames) {
			System.out.println(string);
		}
		
		System.out.println();
		
		
		// 2. 위 작업은 stream으로 처리한다면 더욱 간단하게 처리할 수가 있다.
		// 중간 연산이므로 Stream으로 받는다.
		// stream으로 변환(list.stream()) => 연산(map()) => 결과 출력
		// map(): 스트림의 요소에 저장된 값 중에서 원하는 필드만 추출하거나 특정 형태로 변환할 때 사용한다.
//		Stream<String> names=list.stream().map(File::getName);
//		names.forEach(f -> System.out.println(f)); // 최종적으로 이름만 수집한 후 출력
		
		// 한 줄로 표현
		list.stream().map(File::getName).forEach(f -> System.out.println(f));
		
		
		// 3. fruits를 대문자로 변경하고 그것을 새로운 리스트로 생성한 후 출력
		List<String> fruits=Arrays.asList("melon","apple","banana","grape");
		List<String> upper=new ArrayList<String>();
		for (String string : fruits) {
			upper.add(string.toUpperCase());
		}
		for (String string : upper) {
			System.out.println(string);
		}
		
		System.out.println();
		
		fruits.stream().map(s -> s.toUpperCase()).forEach(s -> System.out.println(s));
		
		System.out.println();
		
		fruits.stream().map(String::toUpperCase).forEach(System.out::println); // 클래스명::메서드명
	}

}
