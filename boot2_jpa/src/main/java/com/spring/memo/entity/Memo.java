package com.spring.memo.entity;

import javax.persistence.Column;
import javax.persistence.Entity; // 클래스를 엔티티로 선언하겠다.
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
// name: 임의로 이름 설정(필수 작업), sequenceName: mem_seq.nextval의 mem_seq 부분, allocationSize = 증가할 숫자
// 콘솔창 출력 내용: Hibernate: create sequence mem_seq start with 1 increment by 1
@SequenceGenerator(name="mem_seq_gen", sequenceName="mem_seq", allocationSize=1)
@Entity // 클래스를 엔티티로 선언
@Setter @Getter @ToString @AllArgsConstructor @NoArgsConstructor 
public class Memo {
	
	// GenerationType: 1) AUTO: JPA 구현체가 자동으로 구현, 2) IDENTITY: 기본키 생성을 데이터베이스에 위임(너네가 알아서 해),
	//                 3) SEQUENCE: @SequenceGenerator 등록 후에 사용 가능, 
	//                 4) TABLE: 키 생성용 테이블 사용 => @TableGenerator 사용
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="mem_seq_gen")
	@Id // pk 생성
	private Long mno;
	
	@Column(length=200, nullable=false) // 테이블 열 자의적 지정
	private String memoText;

}
