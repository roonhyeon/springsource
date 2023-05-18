package com.spring.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
public class Criteria {
	private int pageNum; // 페이지 번호
	private int amount; // 한 페이지당 몇 개의 게시물을 보여줄 것인가
	
	private String type; // 검색조건
	private String keyword; // 검색어
	
	public Criteria() {
		this(1,10); // 디폴트 생성자: 1페이지 당 10개
	}

	public Criteria(int pageNum, int amount) {
		super();
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	

}
