package com.spring.factorial;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// 횡단 관심사를 처리해줄 클래스
@Component
@Aspect
public class TimeAspect {
	@Around(value="execution(* com.spring.factorial..*(..))") // 팩토리얼 패키지 밑의 어떤 것이 오더라도
	public Object measure(ProceedingJoinPoint pjp) {
		long start=System.nanoTime();
		Object obj=null;
		
		try {
			obj=pjp.proceed(); // 주된 관심사를 처리하는 부분 => around를 사용하려면 이렇게 메인 메서드 실행을 해야한다.	
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			long end=System.nanoTime();
			System.out.println("걸린 시간: "+(end-start));
		}
		return obj;
	}

}
