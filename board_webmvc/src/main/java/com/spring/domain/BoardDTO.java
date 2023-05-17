package com.spring.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor @ToString
public class BoardDTO {
	private int bno; // 많이 들어가면 long도 가능
	private String title;
	private String content;
	private String writer;
	private Date regDate;
	private Date updateDate;

}
