package com.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 이 클래스는 환경설정을 위한 클래스라는 것을 알려주는 어노테이션
public class SecurityConfig {
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/* <csrf().disable() 코드의 의미>
	 * spring-boot-starter-security: 시큐리티 라이브러리 모음
	 * 프로젝트에 시큐리티가 적용이 되어 버린다 => 이 라이브러리들로 인해 form login 동작이 되어버린다.
	 * 그러나 여기서 우리는 비밀번호 암호화 코드만 필요하다.
	 * 그렇기 때문에 csrf().disable()을 코드로 작성해줘서 '난 csrf 안 쓸 거야. 기본 코드만 사용할 거야. 시큐리티 라이브러리 안 쓸 거야.'가 된다.
	 */
	
	@Bean // localhost:8080을 주소창에 입력했을 때, 로그인 페이지로 가지 말라고 쓰는 코드
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests().anyRequest().permitAll().and().httpBasic().and().csrf().disable();
		
		return http.getOrBuild();
	}

}
