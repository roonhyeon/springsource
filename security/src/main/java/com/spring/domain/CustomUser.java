package com.spring.domain;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

/*
 * java.util.Collection interface
 * - java.util.List interface
 *    ㄴ java.util.ArrayList
 *    ㄴ java.util.LinkedList
 *    
 * - java.util.Set interface
 *    ㄴ java.util.HashSet
 * 
 * - ? extends GrantedAuthority: GrantedAuthority와 자손들만 가능
 * ==> List<GrantedAuthority>, Set<GrantedAuthority>와 자손들
 * ==> dto.getAuthorities()는 List<SpUserAuthorityDTO>를 갖고 있기 때문에 타입 형태가 맞지 않는다. 따라서 ==> SpUserAuthorityDTO 안의 요소들을
 * 각각 GrantedAuthority의 형태로 변환(객체 생성하기)해주면 된다.
 */

@Getter
public class CustomUser extends User {
	
	private SpUserDTO dto;

	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public CustomUser(SpUserDTO dto) {
		super(dto.getUserid(), dto.getPassword(), dto.getAuthorities().stream()
				.map(auth -> new SimpleGrantedAuthority(auth.getAuthority())) // role 요소를 모은다. SimpleGrantedAuthority(String role)
				.collect(Collectors.toList())); // 모은 role을 List 형태로 만들어준다.
		this.dto = dto;
	}

}
