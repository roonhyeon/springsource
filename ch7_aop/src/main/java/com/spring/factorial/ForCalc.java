package com.spring.factorial;

import org.springframework.stereotype.Component;

@Component("forc")
public class ForCalc implements Calculator {

	@Override
	public long factorial(long num) {
		// for 문으로 factorial 구하기
		int result=1;
		for (int i = 1; i <= num; i++) {
			result*=i;
		}
		return result;
	}

}
