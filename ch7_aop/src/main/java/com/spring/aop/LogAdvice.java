package com.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

// 횡단 관심사를 처리해줄 클래스
// 횡단 관심사를 언제 처리할 것인가?
// Before(메서드 호출 전에), After returning(메서드 호출 이후에), After throwing(메서드 예외 발생 후)
// After(메서드 호출 후 예외 발생 여부와 관계없이), Around(메서드 호출 이전과 이후 모두 적용-가장 많이 사용)

@Component("log")
@Aspect
public class LogAdvice {
	// execution(): 표현식 => 특정 메서드만 advice을 함
	// *: 리턴타입
	// com.spring.aop: 패키지명
	// Product: 클래스명
	// getInfo(): 메서드명
//	@Before(value="execution(* com.spring.aop.Product.getInfo())") // 이 메서드 실행 전에 로그 출력
//	public void beforeLog() {
//		System.out.println("[공통로그] 비즈니스 로직 수행 전 호출");
//	}
	
//	@After(value="execution(* com.spring.aop.Product.getInfo())") // 이 메서드 실행 후에 로그 출력, 해당 메서드가 예외가 발생해도 상관없이 무조건 출력
//	public void afterLog() {
//		System.out.println("[공통로그] 비즈니스 로직 수행 후 호출(예외 발생 여부와 관계없이)");
//	}
	
//	@AfterThrowing(value="execution(* com.spring.aop.Product.getInfo())") 
//	public void afterThrowingLog() {
//		System.out.println("[공통로그] 비즈니스 로직 수행 후 호출됨(예외 발생 시에만 호출)");
//	}
	
//	@AfterReturning(value="execution(* com.spring.aop.Product.getInfo())") 
//	public void afterReturningLog() {
//		System.out.println("[공통로그] 비즈니스 로직 수행 후 호출됨(예외가 발생 안 할 때만 호출)");
//	}
	
	@Around(value="execution(* com.spring.aop.Product.getInfo())") 
	public void aroundLog(ProceedingJoinPoint pjp) {
		System.out.println("[공통로그] 비즈니스 로직 수행 전 호출됨");
		try {
			pjp.proceed(); // 처리해야 하는 메서드 호출됨
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("[공통로그] 비즈니스 로직 수행 후 호출됨");
	}

}
