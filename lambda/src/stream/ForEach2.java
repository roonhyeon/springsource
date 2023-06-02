package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForEach2 {
	public static void main(String[] args) {

		List<String> list=Arrays.asList("A","B","C","D","E");

		// list.stream().forEach(null);
		list.forEach(item -> System.out.println(item));
		list.forEach(System.out::println);
		
		List<Student> stuList=new ArrayList<Student>();
		stuList.add(new Student("홍길동", 99));
		stuList.add(new Student("고길동", 89));
		stuList.add(new Student("김길동", 79));
		stuList.add(new Student("최길동", 69));
		
		stuList.forEach(student -> System.out.println(student.getName()+" : "+student.getJumsu())); // 하나짜리라면 괄호 생략 가능

//		for (Student student2 : stuList) {
//			System.out.println(student2.getName()+" : "+student2.getJumsu());
//		}
		
		// Map: (key, value) 형태
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("A", 10);
		map.put("B", 20);
		map.put("C", 30);
		map.put("D", 40);
		map.put("E", 50);
		map.put("F", 60);
		map.put("G", 70);
	
		// Map 데이터 처리(기존 방식)
		for(Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println("Item : "+entry.getKey()+", Count : "+entry.getValue());
		}
		
		System.out.println();
		
		// forEach 구조로 가게되면 Map이 엄청 편리해진다.
		map.forEach((k,v) -> System.out.println("Item : "+k+", Count : "+v));
		
		// key 값이 E일 때 'hello, E' 출력문을 추가하고 싶다.
		map.forEach((k,v) -> {
			System.out.println("Item : "+k+", Count : "+v);
			if("E".equals(k)) {
				System.out.println("hello, E");
			}
		});
	}
}
