package stream;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamEx2 {
	public static void main(String[] args) {
		List<String> list=Arrays.asList("바둑","바나나","포도","딸기","바질","강아지");
		
		// '바'로 시작하는 요소를 새로운 리스트에 추가 후 출력 
		List<String> ba=new ArrayList<String>();
		for (String string : list) {
			if(string.startsWith("바")){
				ba.add(string); // 새로운 리스트에 담기
			}
		}
		for (String string : ba) {
		    System.out.println(string);
		}
		
		// filter(): 스트림 요소를 걸러낼 때 사용한다.
		// filter를 사용하기 위해 stream으로 변환해준다.
		list.stream().filter(string -> string.startsWith("바")).forEach(System.out::println);
		
		List<Student> stuList=new ArrayList<Student>();
		stuList.add(new Student("홍길동", 99));
		stuList.add(new Student("고길동", 89));
		stuList.add(new Student("김길동", 79));
		stuList.add(new Student("최길동", 69));
		stuList.add(new Student("박길동", 75));
		
		// 이름의 시작이 '김'으로 시작하는 학생들의 이름 출력
		for (Student stu : stuList) {
			if(stu.getName().startsWith("김")) {
				System.out.println(stu.getName());
			}
		}
		
		System.out.println();
		
		stuList.stream().filter(stu -> stu.getName().startsWith("김"))
					 // .forEach(s -> System.out.println(s.getName()));
						.forEach(System.out::println); // toString 형태로 출력된다.
	
		System.out.println();
		
		// distinct(): 중복 제거
		List<String> list2=Arrays.asList("바둑","바나나","포도","딸기","바질","바둑");
		list2.stream().distinct().forEach(System.out::println);
		
		// Arrays.asList(....): array => list
		// Stream.of(....): array => stream
		Stream<File> stream=Stream.of(new File("d:\\test1.txt"), new File("d:\\test2.txt"), new File("d:\\test3.txt"),
				new File("d:\\test1.java"), new File("d:\\test1.bak"), new File("d:\\test"));
	
		// 파일 확장자 추출한 후 중복을 제거하고 출력하기
		// 파일명 추출 => 확장자 추출 => 확장자만 모음 => 중복 제거 => 출력
		// peak(): 연산 중간 결과 확인
		stream.map(File::getName)
			  .filter(f -> f.indexOf(".") > -1)
			  .peek(f -> System.out.println("필터 통과 후 "+f))
			  .map(f -> f.substring(f.lastIndexOf(".")+1))
			  .distinct()
			  .forEach(System.out::println);
	
	}
	
}
