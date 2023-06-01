package com.spring.domain;

import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class BookDTO {
	private int code;
	private String title;
	private String writer;
	private int price;
	private String description;

}
