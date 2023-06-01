package com.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Setter;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor @ToString
public class LoginDTO {
	private String userid;
	private String password;

}
